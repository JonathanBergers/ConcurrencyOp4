package actors;

import akka.actor.ActorRef;

/**
 * Created by jonathan on 19-1-16.
 */
public class TestActor extends ZiggoMember {


    private final ActorRef ziggoDome;

    public TestActor(ActorRef ziggoDome) {
        this.ziggoDome = ziggoDome;
    }


    @Override
    public void onReceive(Object message) throws Exception {

    }
}
