package actors;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by jonathan on 18-1-16.
 */
public abstract class ZiggoMember extends UntypedActor {




    protected LoggingAdapter log(){
        return Logging.getLogger(getContext().system(), this);
    }



    final int id;
    static int idCounter = 0;

    public ZiggoMember() {
        this.id = idCounter;
        idCounter ++;

    }



    @Override
    public String toString() {
        return getClass().getSimpleName()  +  " ID: " + id + " ";
    }



}
