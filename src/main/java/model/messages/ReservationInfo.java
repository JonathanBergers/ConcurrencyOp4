package model.messages;

import lombok.Getter;
import model.Reservation;

/**
 * Created by jonathan on 16-1-16.
 *
 *
 * Dit is het bericht dat de section admin terug stuurt naar de klant.
 */

public class ReservationInfo extends ReservationMessage {



    @Getter
    private final ReservationMessage request;

    @Getter
    private final boolean reservationPossible;


    public ReservationInfo(Reservation reservation, final boolean possible, final ReservationMessage request) {
        super(reservation);
        this.reservationPossible = possible;
        this.request = request;
    }

    @Override
    public String toString() {

        String s = "Reservation info ";
        if(reservationPossible){
            s+= " Possible ";
        }else{
            s+= " Not possible ";
        }

        s+= request.getPath();

        return s;

    }
}
