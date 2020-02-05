package pl.bemowski.ms.router.handler;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class PassengerRequestHandler implements RequestHandler {
    private Logger logger = LoggerFactory.getLogger(PassengerRequestHandler.class);
    private Vertx vertx;

    public PassengerRequestHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    public void handle(RoutingContext event) {


    }
}
