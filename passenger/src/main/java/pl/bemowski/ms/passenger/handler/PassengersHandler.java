package pl.bemowski.ms.passenger.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.jackson.JacksonCodec;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.PassengerEvent;
import pl.bemowski.ms.database.passanger.PassangerStorage;

import java.util.List;

public class PassengersHandler {
    private Logger logger = LoggerFactory.getLogger(PassengersHandler.class);
    private PassangerStorage passengerStorage;

    public PassengersHandler(PassangerStorage passengerStorage) {
        this.passengerStorage = passengerStorage;
    }

    public void handle(Message<String> message) {
        logger.info("Handle message");
        List<PassengerEvent> passengerEvents = JacksonCodec.decodeValue(message.body(), new TypeReference<List<PassengerEvent>>() {
        });
        passengerEvents.forEach(passengerStorage::save);
    }
}
