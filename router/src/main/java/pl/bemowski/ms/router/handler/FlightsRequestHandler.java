package pl.bemowski.ms.router.handler;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class FlightsRequestHandler implements RequestHandler {
    private final Vertx vertx;
    private Logger logger = LoggerFactory.getLogger(FlightsRequestHandler.class);

    public FlightsRequestHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext event) {
        logger.info("Handle flight request");
    }
}
