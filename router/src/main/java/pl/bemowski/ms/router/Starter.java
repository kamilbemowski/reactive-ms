package pl.bemowski.ms.router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import pl.bemowski.ms.router.handler.*;

public class Starter extends AbstractVerticle {


    private final Logger logger = LoggerFactory.getLogger(Starter.class);

    @Override
    public void start() {
        Router router = Router.router(vertx);
        RequestHandler passengerHandler = new PassengerRequestHandler(vertx);
        RequestHandler flightsHandler = new FlightsRequestHandler(vertx);
        RequestHandler airlineHandler = new AirlineRequestHandler(vertx);
        RequestHandler allDataHandler = new AllDataRequestHandler(vertx);
        router.route(HttpMethod.PUT, "/passengers").handler(passengerHandler::handle);
        router.route(HttpMethod.PUT, "/flights").handler(flightsHandler::handle);
        router.route(HttpMethod.PUT, "/airlines").handler(airlineHandler::handle);
        router.route(HttpMethod.PUT, "/all").handler(allDataHandler::handle);
        vertx.createHttpServer().requestHandler(router).listen(8012);
    }


}
