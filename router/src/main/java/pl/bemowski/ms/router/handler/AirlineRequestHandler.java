package pl.bemowski.ms.router.handler;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class AirlineRequestHandler implements RequestHandler {

    private final Vertx vertx;
    private final Logger logger = LoggerFactory.getLogger(AirlineRequestHandler.class);

    public AirlineRequestHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext event) {
    }
}
