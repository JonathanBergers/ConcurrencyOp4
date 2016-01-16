package model.messages;

import lombok.Builder;

/**
 * Created by jonathan on 16-1-16.
 *
 *
 * Dit is het bericht dat de section admin terug stuurt naar de klant.
 */
@Builder
public class ReservationResponse {

    private final boolean reservationPossible;
}
