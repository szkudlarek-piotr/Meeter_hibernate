package org.example.meeter.visits;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.people.HumanTileDto;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class VisitDto {
    private String shortDesc;
    @Size(min=32, max=10240)
    private String longDesc;
    private LocalDateTime visitDate;
    @Size(min=1, message = "Wizyta powinna trwać co najmniej jeden dzień.")
    private int duration;
    private List<HumanTileDto> guests;
}
