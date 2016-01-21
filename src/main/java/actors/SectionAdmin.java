package actors;

import akka.actor.Props;
import model.Chair;
import model.Section;
import model.messages.*;
import model.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 15-1-16.
 *
 * Beheerder van vakken
 * Krijgt:
 *
 * Reservation
 * ReservationConfirmation
 *
 *
 */
public class SectionAdmin extends ZiggoMember{



    static Props create(Section section){
        return Props.create(SectionAdmin.class, () -> new SectionAdmin(section));
    }

    public SectionAdmin(Section section) {
        this.section = section;
    }
    private final Section section;


    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof ReservationMessage){
            ((ReservationMessage) message).mark(this);



            Reservation reservation = ((ReservationMessage) message).getReservation();

            if (message instanceof ReservationRequest){
                List<Chair> chairs = new ArrayList<>();

                // check if all chairs are available for reservation
                boolean reservationPossible = true;

                for (Integer i: reservation.getChairNumbers()){

                    if(!reservationPossible){
                        break;
                    }
                    if(!section.hasChair(i)){
                        reservationPossible = false;
                        break;
                    }

                    Chair chair = section.getChair(i);
                    assert chair != null;

                    if(chair.hasState(Chair.ChairState.FREE)){
                        chairs.add(chair);
                    }else{
                        reservationPossible = false;
                    }
                }

                log().info(toString() + " reservation possible ? : " + reservationPossible);
                ReservationInfo response = null;
                // return decision
                if(reservationPossible){
                    chairs.forEach(chair -> chair.setState(Chair.ChairState.RESERVED));

                    response = new ReservationInfo(reservation, true, (ReservationMessage) message);
                    // send okay message
                }else{
                    response = new ReservationInfo(reservation, false, (ReservationMessage) message);
                    // send not okay message
                }

                getSender().tell(response, getSelf());

                return;

            }

            if(message instanceof ReservationConfirmation){

                ReservationConfirmation decision = (ReservationConfirmation) message;
                List<Chair> boughtChairs = new ArrayList<>();
                for (Integer i: reservation.getChairNumbers()){

                    Chair chair = section.getChair(i);
                    assert chair != null;
                    assert chair.hasState(Chair.ChairState.RESERVED): "chair isnt reserved.." + chair;

                    switch (decision.getType()){
                        case BUY:
                            chair.setState(Chair.ChairState.TAKEN);
                            boughtChairs.add(chair);
                            break;
                        case CANCEL:
                            chair.setState(Chair.ChairState.FREE);
                            break;
                    }
                }

                ReservationResult reservationResult = null;

                switch (decision.getType()){
                    case BUY:
                        assert boughtChairs.size() == reservation.getChairNumbers().length;
                        reservationResult = new ReservationResult(reservation, boughtChairs, (ReservationMessage) message, true);
                        break;
                    case CANCEL:
                        assert boughtChairs.size() == 0;
                        reservationResult = new ReservationResult(reservation, boughtChairs, (ReservationMessage) message, false);
                        break;
                }

                getSender().tell(reservationResult, getSelf());



            }





        }else{
            unhandled(message);
        }




        }



}
