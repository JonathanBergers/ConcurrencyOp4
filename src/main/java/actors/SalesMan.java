package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import model.messages.ReservationConfirmation;
import model.Reservation;
import model.messages.ReservationMessage;
import model.messages.ReservationRequest;

/**
 * Created by jonathan on 15-1-16.
 * Krijgt:
 *
 * Reservation:
 * Customer decision
 *
 * Wanneer klant kaartje wil kopen dan zegt ie dit tegen wing agent
 *
 */
public class SalesMan extends ZiggoMember{

    public static Props create(ActorRef wingManager){
        return Props.create(SalesMan.class, () ->new SalesMan(wingManager));
    }


    private final ActorRef wingManager;

    public SalesMan(ActorRef wingManager) {
        this.wingManager = wingManager;

    }


    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof ReservationMessage){

            ((ReservationMessage) message).mark(this);
            wingManager.tell(message, getSender());
        }else{
            unhandled(message);
        }

    }




    /*

    get try buy
    ask wingagent, are seats available, (try reservate


     */
}
