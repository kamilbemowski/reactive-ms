package pl.bemowski.ms.passenger.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.PassangerEvent;
import pl.bemowski.ms.database.passanger.PassangerStorage;

public class PassengersHandler {
    private Logger logger = LoggerFactory.getLogger(PassengersHandler.class);
    private PassangerStorage passengerStorage;

    public PassengersHandler(PassangerStorage passengerStorage) {
        this.passengerStorage = passengerStorage;
    }

    public void handle(Message<String> message) {
        logger.info("Handle message");
        PassangerEvent passangerEvent = Json.decodeValue(message.body(), PassangerEvent.class);
        passengerStorage.save(passangerEvent);
    }
}
