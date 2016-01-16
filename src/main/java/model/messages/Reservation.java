package model.messages;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

/**
 *  *
 * Dit is het bericht dat de klant stuurt naar de salesman (via de host) om te vragen of hij een reservering kan doen.
 * Dit bericht wordt via de wingman ge forward naar de bijbehorende section admin.
 *
 * Wanneer de section niet bestaat dan wordt er een error message gereturned
 *

 *
 *
 */
@Builder
public class Reservation {

    @Getter
    private final int sectionNumber;
    @Getter
    @Singular
    private final List<Integer> chairNumbers;



}
