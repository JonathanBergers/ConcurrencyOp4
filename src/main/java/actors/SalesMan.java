package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.Routee;
import akka.routing.Router;
import akka.routing.SmallestMailboxRoutingLogic;
import model.messages.CustomerDecision;
import model.messages.Reservation;

import java.util.ArrayList;
import java.util.List;

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

        log().info( toString() + "RECIEVED: " + message.toString());

        // send to wing manager
        if (message instanceof Reservation){
            wingManager.tell(message, getSender());
            // stuur door naar wing agent
        }

        if(message instanceof CustomerDecision){
            wingManager.tell(message, getSender());
            // stuur door naar wing agennt..
        }
        unhandled(message);

        // dump message

    }




    /*

    get try buy
    ask wingagent, are seats available, (try reservate


     */
}
