package pl.bemowski.ms.router.handler;

import io.vertx.ext.web.RoutingContext;

public interface RequestHandler {
    void handle(RoutingContext event);
}
