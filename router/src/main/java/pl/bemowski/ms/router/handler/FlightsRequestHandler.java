package pl.bemowski.ms.router.handler;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import pl.bemowski.ms.common.model.EventBusAddresses;

public class FlightsRequestHandler implements RequestHandler {
    private final Vertx vertx;
    private Logger logger = LoggerFactory.getLogger(FlightsRequestHandler.class);

    public FlightsRequestHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext event) {
        logger.info("Handle flight request");
        event.request().handler(buffer -> {
            String flightMessage = buffer.toString();
            logger.info("Flight message: " + flightMessage);
            vertx.eventBus()
                    .publish(EventBusAddresses.FLIGHT, flightMessage)
                    .request(EventBusAddresses.SCHEDULE, flightMessage, replay -> {
                        logger.info("schedule replay: " + replay.result());
                        if (replay.failed()) {
                            String message = replay.cause().getMessage();
                            this.vertx.eventBus().publish(EventBusAddresses.DELETE_FLIGHT, flightMessage);
                            event.response().setStatusCode(200).end(message);
                        } else {
                            String message = (String) replay.result().body();
                            event.response().setStatusCode(201).end(message);
                            vertx.eventBus().publish(EventBusAddresses.TIMETABLE, flightMessage);
                        }
                    });
        });
    }
}
