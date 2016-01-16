package actors;

import akka.actor.UntypedActor;
import model.messages.CustomerDecision;
import model.messages.Reservation;

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
public class SalesMan extends UntypedActor{


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Reservation){

            // stuur door naar wing agent


        }

        if(message instanceof CustomerDecision){

            // stuur door naar wing agennt..

        }

        // dump message

    }




    /*

    get try buy
    ask wingagent, are seats available, (try reservate


     */
}
