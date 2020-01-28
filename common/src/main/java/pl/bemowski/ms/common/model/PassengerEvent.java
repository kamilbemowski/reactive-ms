package pl.bemowski.ms.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import pl.bemowski.ms.common.json.LocalDateDeserializer;
import pl.bemowski.ms.common.json.LocalDateSerializer;

import java.time.LocalDate;

@Data
public class PassengerEvent implements Key {
    private Long id;
    private String firstName;
    private String lastName;
    private String flight;
    private String airline;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private PassengerFlightStatus status;

    @Override
    public String key() {
        return firstName + lastName + flight + date.toString();
    }
}
