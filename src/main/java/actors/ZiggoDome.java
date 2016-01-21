package actors;

import akka.actor.*;
import model.Chair;
import model.Section;
import model.Wing;
import model.messages.ReservationConfirmation;
import model.Reservation;
import model.messages.ReservationMessage;
import model.messages.ReservationRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 16-1-16.
 *
 *  * Ontvangt alle koop verzoeken van klanten en stuurt deze door naar de meest geschikte salesman
 * Router
 *
 * Ontvangen messages
 * Reservation :
 * Customer decision:
 *
 * stuurt deze allemaal door naar de juiste salesman
 */
public class ZiggoDome extends ZiggoMember{


    public static Props create(int amountOfSalesmen){
        return Props.create(ZiggoDome.class, () -> new ZiggoDome(amountOfSalesmen));
    }

    private final ActorRef wingManager;
    private final ActorRef salesManager;
    private List<Wing> wings = new ArrayList<>();
    private List<String> wingLabels = new ArrayList<>();


    public ZiggoDome(int amountOfSalesmen){
        initData();
        wingManager = getContext().actorOf(WingManager.create(wings));
        salesManager = getContext().actorOf(SalesManager.create(amountOfSalesmen, wingManager));

    }

    private void initData(){



        wingLabels.add("vloer");
        wingLabels.add("eerste ring noord");
        wingLabels.add("eerste ring west");
        wingLabels.add("eerste ring zuid");

        wingLabels.add("tweede ring noord");
        wingLabels.add("tweede ring west");
        wingLabels.add("tweede ring zuid");

        for(String s: wingLabels){

            List<Section> sections = new ArrayList<>();
            // create 2 sections

            sections.add(new Section(1));
            sections.add(new Section(2));

            // add 5 chairs to the sections
            for (int i = 0; i < 5; i++) {
                final int finalI = i;
                sections.forEach(section -> section.addChair(new Chair(finalI)));
            }

            this.wings.add(new Wing(s, sections));
        }

    }

    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof ReservationMessage){
            ((ReservationMessage) message).mark(this);
            salesManager.tell(message, getSender());
        }else{
            unhandled(message);
        }



    }



}
