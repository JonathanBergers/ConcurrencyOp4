package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import model.Wing;
import model.messages.CustomerDecision;
import model.messages.Reservation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jonathan on 19-1-16.
 */
public class WingManager extends ZiggoMember {

    public static Props create(List<Wing> wings){
        return Props.create(WingManager.class ,() -> new WingManager(wings));

    }

    private final HashMap<String, ActorRef> wingAgents = new HashMap<>();

    public WingManager(List<Wing> wings) {
        wings.forEach(wing -> wingAgents.put(wing.getLabel(), getContext().actorOf(WingAgent.create(wing))));
    }



    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof Reservation){
            Reservation r = (Reservation) message;
            assert wingAgents.containsKey(r.getWing());
            // send to the right wing
            wingAgents.get(r.getWing()).tell(message, getSender());
        }
        if(message instanceof CustomerDecision){
            CustomerDecision r = (CustomerDecision) message;
            assert wingAgents.containsKey(r.getReservation().getWing());
            // send to the right wing
            wingAgents.get(r.getReservation().getWing()).tell(message, getSender());
        }

    }
}
