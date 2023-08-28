package pl.kargolek.process.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CampSlots {
    private Integer availableSlots;
    private Integer maxSlots;
}
