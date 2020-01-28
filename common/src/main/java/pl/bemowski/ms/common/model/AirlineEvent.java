package pl.bemowski.ms.common.model;

import lombok.Data;

@Data
public class AirlineEvent implements Key {

    private String name;
    private String abbreviation;

    @Override
    public String key() {
        return abbreviation;
    }
}
