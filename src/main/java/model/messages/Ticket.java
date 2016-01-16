package model.messages;

import lombok.Builder;
import model.Chair;

import java.util.List;

/**
 * Created by jonathan on 16-1-16.
 *
 * dit is het bericht dat de klant krijgt wanneer hij een plek heeft gekocht
 * de section admin stuurt deze
 */
@Builder
public class Ticket {

    private final List<Chair> chairs;



}
