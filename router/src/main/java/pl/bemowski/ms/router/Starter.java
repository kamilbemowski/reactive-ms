package pl.bemowski.ms.router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import pl.bemowski.ms.router.handler.PassangerRequestHandler;

public class Starter extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.route(HttpMethod.PUT, "passengers").handler(
                new PassangerRequestHandler(vertx)
        );
        httpServer.listen(8012);
    }
}
