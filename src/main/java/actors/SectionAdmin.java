package actors;

import akka.actor.Props;
import akka.actor.UntypedActor;
import model.Chair;
import model.Section;
import model.Wing;
import model.messages.CustomerDecision;
import model.messages.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 15-1-16.
 *
 * Beheerder van vakken
 * Krijgt:
 *
 * Reservation
 * CustomerDecision
 *
 *
 */
public class SectionAdmin extends UntypedActor implements ActorCreator<Wing, Integer> {


    @Override
    public Props getProps(Wing data, Integer parameter) {
        return null;
    }

    public SectionAdmin(Section section) {
        this.section = section;
    }

    public static Props props(final Section section){
        return new Props(() -> {
            return new SectionAdmin(section);
        });
    }

    /**
     * Build section
     */
    @Override
    public void preStart() {
        super.preStart();
    }

    private final Section section;


    @Override
    public void onReceive(Object message) throws Exception {


            if (message instanceof Reservation){
                Reservation reservation = (Reservation) message;
                List<Chair> chairs = new ArrayList<>();


                // check if all chairs are available for reservation
                boolean reservationPossible = true;

                for (Integer i: reservation.getChairNumbers()){

                    if(!reservationPossible){
                        break;
                    }
                    if(!section.hasChair(i)){
                        reservationPossible = false;
                    }

                    Chair chair = section.getChair(i);
                    assert chair != null;

                    if(chair.hasState(Chair.ChairState.FREE)){
                        chairs.add(chair);
                    }else{
                        reservationPossible = false;
                    }
                }

                // return decision
                if(reservationPossible){
                    chairs.forEach(chair -> chair.setState(Chair.ChairState.RESERVED));
                    // send okay message
                }else{
                    // send not okay message
                }
                return;

            }

        if(message instanceof CustomerDecision){
            CustomerDecision decision = (CustomerDecision) message;
            Reservation reservation = decision.getReservation();

            for (Integer i: reservation.getChairNumbers()){

                Chair chair = section.getChair(i);
                assert chair != null;
                assert chair.hasState(Chair.ChairState.RESERVED): "chair isnt reserved.." + chair;

                switch (decision.getType()){
                    case BUY:
                        chair.setState(Chair.ChairState.TAKEN);
                        break;
                    case CANCEL:
                        chair.setState(Chair.ChairState.FREE);
                        break;
                }
            }


        }


        }



}
