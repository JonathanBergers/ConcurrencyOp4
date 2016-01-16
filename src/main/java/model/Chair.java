package model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jonathan on 16-1-16.
 */
public class Chair {

    @Getter
    private final int number;
    @Getter @Setter
    private ChairState state;

    public Chair(final int number, ChairState state) {
        this.number = number;
        this.state = state;
    }

    public Chair(final int number) {
        this.number = number;
        this.state = ChairState.FREE;
    }

    public enum ChairState{
        FREE, RESERVED, TAKEN;
    }

    public boolean hasNumber(final int number){
        return this.number == number;
    }

    public boolean hasState(final ChairState state){
        return this.state.equals(state);
    }


    @Override
    public String toString() {
        return "Chair{" +
                "number=" + number +
                ", state=" + state +
                '}';
    }
}
