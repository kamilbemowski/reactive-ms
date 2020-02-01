package pl.bemowski.ms.flight.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import pl.bemowski.ms.common.model.FlightEvent;
import pl.bemowski.ms.database.flight.FlightStorage;

public class FlightHandler {
    private final FlightStorage flightStorage;

    public FlightHandler(FlightStorage flightStorage) {
        this.flightStorage = flightStorage;
    }

    public void handle(Message<String> message) {
        FlightEvent flightEvent = Json.decodeValue(message.body(), FlightEvent.class);
        flightStorage.save(flightEvent);
    }
}
