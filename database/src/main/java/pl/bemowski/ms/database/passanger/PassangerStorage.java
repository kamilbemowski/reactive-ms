package pl.bemowski.ms.database.passanger;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.MultiMap;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.PassengerEvent;

import java.time.LocalDate;
import java.util.Collection;

public class PassangerStorage {
    private Logger logger = LoggerFactory.getLogger(PassangerStorage.class);
    private MultiMap<String, PassengerEvent> passengers;

    public PassangerStorage() {
        passengers = Hazelcast.getAllHazelcastInstances().iterator().next().getMultiMap("passengers");
        passengers.put("AA12312020-11-11", passengerEvent("jan", "kowalski"));
        passengers.put("AA12312020-11-11", passengerEvent("paweł", "kowalski"));
        passengers.put("AA12312020-11-11", passengerEvent("władysław", "nowak"));
        passengers.put("AA12312020-11-11", passengerEvent("piotr", "kowalski"));
    }

    private PassengerEvent passengerEvent(final String firstName, final String lastName) {
        PassengerEvent passengerEvent = new PassengerEvent();
        passengerEvent.setDate(LocalDate.parse("2020-11-11"));
        passengerEvent.setAirline("AA");
        passengerEvent.setFlight("AA12312");
        passengerEvent.setFirstName(firstName);
        passengerEvent.setLastName(lastName);
        return passengerEvent;
    }

    public void save(PassengerEvent event) {
        logger.info("Save passenger: " + event);
        passengers.put(event.key(), event);
    }

    public Collection<PassengerEvent> getAllByFlightNumberAndDate(String flightNumber, String date) {
        return passengers.get(flightNumber + date);
    }
}
