package pl.bemowski.ms.router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import pl.bemowski.ms.router.handler.AirlineRequestHandler;
import pl.bemowski.ms.router.handler.FlightsRequestHandler;
import pl.bemowski.ms.router.handler.PassengerRequestHandler;
import pl.bemowski.ms.router.handler.RequestHandler;

public class Starter extends AbstractVerticle {


    private final Logger logger = LoggerFactory.getLogger(Starter.class);

    @Override
    public void start() {
        Router router = Router.router(vertx);
        RequestHandler passengerHandler = new PassengerRequestHandler(vertx);
        RequestHandler flightsHandler = new FlightsRequestHandler(vertx);
        RequestHandler airlineHandler = new AirlineRequestHandler(vertx);
        vertx.createHttpServer().requestHandler(router).listen(8012);
    }


}
