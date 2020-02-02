package pl.bemowski.ms.flight;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.common.model.EventBusAddresses;
import pl.bemowski.ms.database.flight.FlightStorage;
import pl.bemowski.ms.flight.handler.FlightEventHandler;

public class FlightRunner extends AbstractVerticle {

    @Override
    public void start() {
        FlightEventHandler handler = new FlightEventHandler(new FlightStorage());
        vertx.eventBus().consumer(EventBusAddresses.FLIGHT, handler::handle);
    }
}
