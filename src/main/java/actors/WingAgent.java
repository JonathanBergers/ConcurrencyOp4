package actors;

import akka.actor.Props;
import akka.actor.UntypedActor;
import model.Section;
import model.Wing;

/**
 * Created by jonathan on 15-1-16.
 *
 * Wing
 */
public class WingAgent extends UntypedActor{


    public WingAgent(Wing wing) {
        this.wing = wing;
    }

    public static Props props(final Wing wing){
        return new Props(() -> {
            return new WingAgent(wing);
        });
    }
    private final Wing wing;


    @Override
    public void onReceive(Object message) throws Exception {

    }
}
