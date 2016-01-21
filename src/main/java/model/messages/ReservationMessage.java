package model.messages;

import lombok.Getter;
import model.Reservation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Created by jonathan on 21-1-16.
 */
public abstract class ReservationMessage {


    @Getter
    private LinkedList<Class> marks = new LinkedList<>();


    public void mark(Object o){
        marks.addLast(o.getClass());
    }



    @Getter
    private final Reservation reservation;

    protected ReservationMessage(Reservation reservation) {
        this.reservation = reservation;
    }


    public String getPath(){

        String s = "Path: ";
        for(Class c: marks){
            s+= c + " -> ";

        }
        return s;


    }


}
