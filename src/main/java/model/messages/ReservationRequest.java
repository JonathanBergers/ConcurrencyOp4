package model.messages;

import model.Reservation;

/**
 * Created by jonathan on 21-1-16.
 */
public class ReservationRequest extends ReservationMessage {

    public ReservationRequest(Reservation reservation) {
        super(reservation);
    }

}
