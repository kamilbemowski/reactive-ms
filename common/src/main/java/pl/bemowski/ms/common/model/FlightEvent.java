package pl.bemowski.ms.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import pl.bemowski.ms.common.json.LocalDateDeserializer;
import pl.bemowski.ms.common.json.LocalDateSerializer;

import java.time.LocalDate;

@Data
public class FlightEvent implements Key {
    private String flightNumber;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private String destination;
    private FlightType type;
    private FlightDirection direction;

    @Override
    public String key() {
        return flightNumber + date.toString() + destination;
    }
}
