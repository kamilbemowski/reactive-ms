package pl.bemowski.ms.router.handler;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class AllDataRequestHandler implements RequestHandler {
    private final Vertx vertx;

    public AllDataRequestHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext event) {

    }
}
