package pl.bemowski.ms.router.handler;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pl.bemowski.ms.common.model.EventBusAddresses;

public class FlightsRequestHandler implements RequestHandler {
    private final Vertx vertx;

    public FlightsRequestHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext event) {
        event.request().handler(buffer -> this.vertx.eventBus().
                publish(EventBusAddresses.FLIGHT, buffer.toString())
                .request(EventBusAddresses.SCHEDULE, buffer.toString(), replay -> {
                    if (replay.failed()) {
                        String message = replay.result().body().toString();
                        this.vertx.eventBus().publish(EventBusAddresses.FLIGHT, "delete");
                        event.response().setStatusCode(200).write(message).end();
                    } else {
                        event.response().setStatusCode(201).end();
                    }
                }));
    }
}
