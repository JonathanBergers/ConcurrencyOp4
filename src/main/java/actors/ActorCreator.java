package actors;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import model.ZiggoDome;

/**
 * Created by jonathan on 18-1-16.
 */
public interface ActorCreator<T, P, A extends ZiggoMember> {



    default public Props create(T data, P parameter, A actor) {
        return actor.creator.create(data, parameter);
    }




}
