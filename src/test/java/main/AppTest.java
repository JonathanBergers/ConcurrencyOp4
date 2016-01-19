package main;

import actors.ZiggoDome;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        ActorSystem actorSystem = ActorSystem.create("Amsterdam");

        ActorRef actorRef = actorSystem.actorOf(ZiggoDome.create(10));


        for (int i = 0; i < 10; i++) {


        }


        assertTrue( true );
    }
}
