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
public class Reservation {


    public Reservation(final String wing, final int sectionNumber, int... chairNumbers) {
        this.wing = wing;
        this.sectionNumber = sectionNumber;
        this.chairNumbers = chairNumbers;
    }

    @Getter
    private final String wing;

    @Getter
    private final int sectionNumber;


    @Getter
    private final int[] chairNumbers;


    @Override
    public String toString() {
        return "Reservation{" +
                "wing='" + wing + '\'' +
                ", sectionNumber=" + sectionNumber +
                ", chairNumbers=" + chairNumbers +
                '}';
    }
}
