package pl.bemowski.ms.flight;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.database.flight.FlightStorage;
import pl.bemowski.ms.flight.handler.FlightHandler;

public class FlightRunner extends AbstractVerticle {

    @Override
    public void start() {
        FlightHandler handler = new FlightHandler(new FlightStorage());
        vertx.eventBus().consumer("flight", handler::handle);
    }
}
