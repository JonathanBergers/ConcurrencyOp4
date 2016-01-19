package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import model.Wing;
import model.messages.CustomerDecision;
import model.messages.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jonathan on 15-1-16.
 *
 * Wing
 */
public class WingAgent extends ZiggoMember{

    private final Wing wing;
    private final HashMap<Integer, ActorRef> sectionAdmins = new HashMap<>();


    public static Props create(Wing wing){
        return Props.create(WingAgent.class, () -> new WingAgent(wing));
    }

    public WingAgent(Wing wing) {
        this.wing = wing;
        wing.getSections().forEach(section -> sectionAdmins.put(section.getNumber(), getContext().actorOf(SectionAdmin.create(section))));


    }



    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof Reservation){
            Reservation r = (Reservation) message;
            assert wingAgents.containsKey(r.get());
            // send to the right wing
            wingAgents.get(r.getWing()).tell(message, getSender());
        }
        if(message instanceof CustomerDecision){
            CustomerDecision r = (CustomerDecision) message;
            assert wingAgents.containsKey(r.getReservation().getWing());
            // send to the right wing
            wingAgents.get(r.getReservation().getWing()).tell(message, getSender());
        }


        log().info( toString() + "RECIEVED: " + message.toString());

    }


}
