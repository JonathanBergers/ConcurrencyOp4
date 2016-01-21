package model.messages;

import lombok.Getter;
import model.Chair;
import model.Reservation;

import java.util.List;

/**
 * Created by jonathan on 16-1-16.
 *
 * dit is het bericht dat de klant krijgt wanneer hij een plek heeft gekocht
 * de section admin stuurt deze
 */

public class ReservationResult extends ReservationMessage {

    @Getter
    private final boolean success;

    @Getter
    private final ReservationMessage request;

    @Getter
    private final List<Chair> chairs;

    public ReservationResult(Reservation reservation, List<Chair> chairs, ReservationMessage request, boolean success) {
        super(reservation);
        this.chairs = chairs;
        this.success = success;
        this.request = request;
    }

    @Override
    public String toString() {

        String s = "ReservationResult: ";

        if(success){
            s += "Successful ";
        }else {
            s += "Unsuccesful ";
        }

        s+= " Chairs " + chairs.toString() + " " + request.getPath();
        return s;



    }
}
