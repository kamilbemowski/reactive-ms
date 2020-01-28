package pl.bemowski.ms.airline;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.airline.handler.AirlineHandler;
import pl.bemowski.ms.database.airline.AirlineStorage;

public class AirlineStarter extends AbstractVerticle {

    @Override
    public void start() {
        AirlineHandler handler = new AirlineHandler(new AirlineStorage());
        vertx.eventBus().consumer("airline", handler::handle);
    }
}
