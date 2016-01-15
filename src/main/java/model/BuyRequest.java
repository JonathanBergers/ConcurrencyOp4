package model;

import lombok.Builder;

/**
 * Created by jonathan on 15-1-16.
 *
 * Klant stuurt dit aan salesman, (indirect) met de vraag of de stoelen vrij zijn voor reservern, en vak
 */
@Builder
public class BuyRequest {


    private final String section;




}
