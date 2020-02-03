package pl.bemowski.ms.flight;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.EventBusAddresses;
import pl.bemowski.ms.database.flight.FlightStorage;
import pl.bemowski.ms.flight.handler.FlightEventHandler;

public class FlightRunner extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(FlightRunner.class);

    @Override
    public void start() {
        logger.info("Start Flight runner");
        FlightEventHandler handler = new FlightEventHandler(new FlightStorage());
        vertx.eventBus().consumer(EventBusAddresses.FLIGHT, handler::handle);
    }
}
