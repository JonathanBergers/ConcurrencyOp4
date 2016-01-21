package model.messages;

import lombok.Getter;
import model.Reservation;

/**
 * Created by jonathan on 16-1-16.
 * Dit bericht wordt gestuurd van de klant naar de salesman
 * wanneer de klant weet of hij een reservering wil bevestigen of cancelen
 */
public class ReservationConfirmation  extends ReservationMessage{


    public ReservationConfirmation(DecisionType type, Reservation reservation) {
        super(reservation);
        this.type = type;

    }

    public enum DecisionType{
        BUY, CANCEL;
    }
    @Getter
    private final DecisionType type;


}
