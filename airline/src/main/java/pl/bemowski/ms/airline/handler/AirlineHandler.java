package pl.bemowski.ms.airline.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.AirlineEvent;
import pl.bemowski.ms.database.airline.AirlineStorage;

public class AirlineHandler {

    private final AirlineStorage airlineStorage;
    private final Logger logger = LoggerFactory.getLogger(AirlineHandler.class);

    public AirlineHandler(AirlineStorage airlineStorage) {
        this.airlineStorage = airlineStorage;
    }

    public void handle(Message<String> message) {
        logger.info("Handle airline info");
        AirlineEvent airlineEvent = Json.decodeValue(message.body(), AirlineEvent.class);
        airlineStorage.save(airlineEvent);
    }

}
