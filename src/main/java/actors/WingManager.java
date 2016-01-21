package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import model.Wing;
import model.messages.ReservationConfirmation;
import model.Reservation;
import model.messages.ReservationMessage;
import model.messages.ReservationRequest;

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

        if(message instanceof ReservationMessage){
            ((ReservationMessage) message).mark(this);
            Reservation r = ((ReservationRequest) message).getReservation();
            assert wingAgents.containsKey(r.getWing());
            wingAgents.get(r.getWing()).tell(message, getSender());
        }else{
            unhandled(message);

        }


    }
}
