package pl.bemowski.ms.flight.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.FlightEvent;
import pl.bemowski.ms.database.flight.FlightStorage;

public class FlightEventHandler {

    private final FlightStorage flightStorage;
    private final Logger logger = LoggerFactory.getLogger(FlightEventHandler.class);

    public FlightEventHandler(FlightStorage flightStorage) {
        this.flightStorage = flightStorage;
    }

    public void handle(Message<String> message) {
        logger.info("Received flight message");
        FlightEvent flightEvent = Json.decodeValue(message.body(), FlightEvent.class);
        flightStorage.save(flightEvent);
    }

}
