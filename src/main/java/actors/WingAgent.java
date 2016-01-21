package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import model.Wing;
import model.messages.ReservationConfirmation;
import model.Reservation;
import model.messages.ReservationMessage;
import model.messages.ReservationRequest;

import java.util.HashMap;

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

        if(message instanceof ReservationMessage){
            ((ReservationMessage) message).mark(this);
            Reservation r = ((ReservationMessage)message).getReservation();

            // forward message to right section admin
            int sectionNr = r.getSectionNumber();
            assert sectionAdmins.containsKey(sectionNr);
            sectionAdmins.get(sectionNr).tell(message, getSender());

        }else{
            unhandled(message);
        }






    }


}
