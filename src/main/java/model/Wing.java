package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jonathan on 16-1-16.
 */
public class Wing {
    @Getter
    private final String label;
    @Getter
    private List<Section> sections = new ArrayList<>();


    public Wing(String label) {
        this.label = label;
    }

    public Wing(String label, List<Section> sections) {
        this.label = label;
        this.sections = sections;
    }

    public void addSection(Section section){
        sections.add(section);
    }

    public Section getSectionByNumber(final int number){
        return sections.stream().filter(section -> section.hasNumber(number)).findFirst().get();
    }


    public boolean hasLabel(final String label){
        return this.label.equals(label);
    }

}
