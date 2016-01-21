package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import model.Chair;
import model.messages.ReservationConfirmation;
import model.Reservation;
import model.messages.ReservationInfo;
import model.messages.ReservationRequest;
import model.messages.ReservationResult;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by jonathan on 16-1-16.
 */
public class Customer extends ZiggoMember {


    public static Props create(ActorRef ziggoDome){
        return Props.create(ZiggoMember.class, () -> new Customer(ziggoDome));
    }

    private final ActorRef ziggoDome;

    public Customer(ActorRef ziggoDome) {
        this.ziggoDome = ziggoDome;

    }



    @Override
    public void onReceive(Object message) throws Exception {


        if(message instanceof Reservation){
            // redirect to ziggodome and make sender itself
            // for testng
            ReservationRequest r = new ReservationRequest((Reservation) message);

            log().info(toString() + " Requesting reservation " + r.getReservation());
            ziggoDome.tell(r, getSelf());
            return;

        }

        if(message instanceof ReservationInfo){
            ReservationInfo r = (ReservationInfo) message;


            log().info(toString() + " GOT INFO " + message.toString());
            //check path
            LinkedList<Class> path = r.getRequest().getMarks();

            assert path.pop().equals(ZiggoDome.class);
            assert path.pop().equals(SalesManager.class);
            assert path.pop().equals(SalesMan.class);
            assert path.pop().equals(WingManager.class);
            assert path.pop().equals(WingAgent.class);
            assert path.pop().equals(SectionAdmin.class);



            if(r.isReservationPossible()){

                ReservationConfirmation d = null;
                // buy
                if(Math.random()> 0.5){
                    d = new ReservationConfirmation(ReservationConfirmation.DecisionType.BUY, r.getReservation());
                }else {
                    d = new ReservationConfirmation(ReservationConfirmation.DecisionType.CANCEL, r.getReservation());
                }
                getSender().tell(d, self());
            }
            return;
        }

        if(message instanceof ReservationResult){

            log().info(toString() + " GOT RESULT " + message.toString());
            ReservationResult r = (ReservationResult) message;

            if(r.isSuccess()){
                // extra checks
                //assert all seats are returned in ticket
                assert r.getChairs().size() == r.getRequest().getReservation().getChairNumbers().length;
                //assert all seats are taken
                assert r.getChairs().stream().filter(chair -> chair.hasState(Chair.ChairState.TAKEN)).collect(Collectors.toList()).size() == r.getRequest().getReservation().getChairNumbers().length;
            }else{
                assert r.getChairs().size() == 0;
            }


            LinkedList<Class> path = r.getRequest().getMarks();
            assert path.size() == 1;
            assert path.pop().equals(SectionAdmin.class);


            return;
        }

        unhandled(message);
    }





}
