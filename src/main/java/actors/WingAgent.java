package actors;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import model.Wing;
import model.ZiggoDome;

/**
 * Created by jonathan on 15-1-16.
 *
 * Wing
 */
public class WingAgent extends ZiggoMember{


    private final T data;
    private final P parameter;

    public ActorCreator(T data, P parameter) {
        this.data = data;
        this.parameter = parameter;
    }

    /**every member implementst this to get their data
     *
     * @param object
     * @return
     */
    public abstract Props create();




    public WingAgent(Wing wing) {
        this.wing = wing;
    }
    private final Wing wing;


    @Override
    public void onReceive(Object message) throws Exception {

    }


    @Override
    public Props getProps(ZiggoDome data, String parameter) {
        return new Props().withCreator((UntypedActorFactory) () -> new WingAgent(data.getWingByLabel(parameter)));
    }
}
