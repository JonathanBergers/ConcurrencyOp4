package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import model.messages.CustomerDecision;
import model.messages.Reservation;
import model.messages.ReservationResponse;

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

        log().info(toString() + "RECIEVED : " + message.toString());

        if(message instanceof Reservation){

            log().info(toString() + " Got command for reservation : " + message.toString());
            // redirect to ziggodome and make sender itself
            // for testng
            ziggoDome.tell(message, getSelf());
            return;

        }

        if(message instanceof ReservationResponse){
            ReservationResponse r = (ReservationResponse) message;

            if(r.isReservationPossible()){

                // buy
                if(Math.random()> 0.5){
                    CustomerDecision d = CustomerDecision.builder().type(CustomerDecision.DecisionType.BUY).build();
                    getSender().tell(d, self());
                }else {
                    // cancle
                    CustomerDecision d = CustomerDecision.builder().type(CustomerDecision.DecisionType.CANCEL).build();
                    getSender().tell(d, self());
                }

            }
            return;
        }



        log().warning("Message not handled");
        unhandled(message);
    }





}
