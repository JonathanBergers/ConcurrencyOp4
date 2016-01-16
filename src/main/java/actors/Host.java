package actors;

import akka.actor.UntypedActor;
import model.messages.Reservation;

/**
 * Created by jonathan on 15-1-16.
 *
 * Ontvangt alle koop verzoeken van klanten en stuurt deze door naar de meest geschikte salesman
 * Router
 *
 * Ontvangen messages
 * Reservation :
 * Customer decision:
 *
 * stuurt deze allemaal door naar de juiste salesman
 *
 */
public class Host extends UntypedActor{


    @Override
    public void preStart() {


        // maak pool van salesmen.

//        salesmen = getContext().actorOf(new RoundRobinPool)

        super.preStart();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        // stuur door
    }
}
