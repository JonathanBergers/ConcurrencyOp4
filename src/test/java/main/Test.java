package main;

import actors.Customer;
import actors.ZiggoDome;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;

/**
 * Created by jonathan on 19-1-16.
 */
public class Test {


    public static void main(String[] args) {


        ActorSystem actorSystem = ActorSystem.create("Amsterdam");
        ActorRef ziggoDome = actorSystem.actorOf(ZiggoDome.create(10));

        List<String> wingLabels = new ArrayList<>();
        wingLabels.add("vloer");
        wingLabels.add("eerste ring noord");
        wingLabels.add("eerste ring west");
        wingLabels.add("eerste ring zuid");

        wingLabels.add("tweede ring noord");
        wingLabels.add("tweede ring west");
        wingLabels.add("tweede ring zuid");



        List<List<ActorRef>> customers = new ArrayList<>();


        for(String s: wingLabels){
            List<ActorRef> customersSegment = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                customersSegment.add(actorSystem.actorOf(Customer.create(ziggoDome)));
            }
            customers.add(customersSegment);


        }



        while(true){

            for (int i = 0; i < wingLabels.size(); i++) {

                List<ActorRef> customersForWing = customers.get(i);
                String wingLabel = wingLabels.get(i);

                for(ActorRef cust : customersForWing){

                    // send reservation for 1 chair in a sectionNumber
                    IntSupplier randomNumber = () -> (int)(Math.random() * 5);

                    int chairNumber = randomNumber.getAsInt();
                    int sectionNumber = randomNumber.getAsInt() > 2 ? 1: 2;

                    Reservation r = new Reservation(wingLabel, sectionNumber, chairNumber);



                    cust.tell(r, null);
                    System.out.println("sent message" + r);
                }


            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }



    }



}
