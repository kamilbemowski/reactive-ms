package pl.bemowski.ms.router.handler;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class PassangerRequestHandler implements Handler<HttpServerRequest> {
    private Vertx vertx;

    public PassangerRequestHandler(Vertx vertx) {

        this.vertx = vertx;
    }

    public void handle(HttpServerRequest event) {
        event.bodyHandler(body -> {
            vertx.eventBus().publish("passengers", new String(body.getBytes()));
        });
    }
}
