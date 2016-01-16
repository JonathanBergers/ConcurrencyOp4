package model.messages;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by jonathan on 16-1-16.
 * Dit bericht wordt gestuurd van de klant naar de salesman
 * wanneer de klant weet of hij een reservering wil bevestigen of cancelen
 */
@Builder
public class CustomerDecision {


    public enum DecisionType{

        BUY, CANCEL;
    }
    @Getter
    private final DecisionType type;
    @Getter
    private final Reservation reservation;


}
