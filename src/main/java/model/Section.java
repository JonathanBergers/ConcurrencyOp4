package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jonathan on 16-1-16.
 */
public class Section {

    @Getter
    private final int number;
    private List<Chair> chairs = new ArrayList<Chair>();

    public Section(int number) {
        this.number = number;
    }

    public void addChair(Chair chair){
        this.chairs.add(chair);
    }


    public Chair getChair(int chairNumber){
      return chairs.stream().filter(chair -> chair.hasNumber(chairNumber)).findFirst().get();
    }

    public boolean hasChair(int chairNumber){
        Optional<Chair> chairOpt = chairs.stream().filter(chair -> chair.hasNumber(chairNumber)).findFirst();
        return chairOpt.isPresent();
    }

    public boolean hasNumber(final int number){
        return this.number == number;
    }

}
