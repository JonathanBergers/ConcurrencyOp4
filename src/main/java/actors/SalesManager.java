package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.Routee;
import akka.routing.Router;
import akka.routing.SmallestMailboxRoutingLogic;
import model.messages.ReservationMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 19-1-16.
 */
public class SalesManager extends ZiggoMember {

    public static Props create(int amountOfSalesMan, ActorRef wingManager){
        return Props.create(SalesManager.class, () -> new SalesManager(amountOfSalesMan, wingManager));
    }



    private final Router salesMen;

    public SalesManager(int amountOfSalesman, ActorRef wingManager) {



        // create the children
        List<ActorRef> salesMenRefs = new ArrayList<>();
        for (int i = 0; i < amountOfSalesman; i++) {
            salesMenRefs.add(getContext().actorOf(SalesMan.create(wingManager)));
        }

        // create routees of actorrefs
        List<Routee> salesMenRoutees = new ArrayList<>();
        salesMenRefs.forEach(actorRef -> salesMenRoutees.add(new ActorRefRoutee(actorRef)));

        // create the router
        salesMen = new Router(new SmallestMailboxRoutingLogic(), salesMenRoutees);


    }



    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof ReservationMessage){
            ((ReservationMessage) message).mark(this);
            salesMen.route(message, getSender());
        }else{
            unhandled(message);
        }



        // if message from customer
        //redirect to children, salesman




    }
}
