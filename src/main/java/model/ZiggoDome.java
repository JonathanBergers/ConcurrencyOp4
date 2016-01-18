package model;

import akka.actor.Actor;
import akka.actor.Props;
import akka.actor.UntypedActorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 16-1-16.
 */
public class ZiggoDome {
    public static void main(String[] args) {


    }

    private List<Wing> wings = new ArrayList<>();
    // 1 host
    // many salesmen
    // many wngagents



    public ZiggoDome(){

        initData();

    }

    private void initData(){

        List<String> wingLabels = new ArrayList<>();
        wingLabels.add("vloer");
        wingLabels.add("eerste ring noord");
        wingLabels.add("eerste ring west");
        wingLabels.add("eerste ring zuid");

        wingLabels.add("tweede ring noord");
        wingLabels.add("tweede ring west");
        wingLabels.add("tweede ring zuid");

        for(String s: wingLabels){

            List<Section> sections = new ArrayList<>();
            // create 10 sections
            for (int i = 0; i < 10; i++) {
                sections.add(new Section(i));
            }
            // add 10 chairs to the sections
            for (int i = 0; i < 10; i++) {
                final int finalI = i;
                sections.forEach(section -> section.addChair(new Chair(finalI)));
            }

            this.wings.add(new Wing(s, sections));
        }

    }

    public Wing getWingByLabel(final String label){
        return wings.stream().filter(wing -> wing.hasLabel(label)).findFirst().get();
    }

}
