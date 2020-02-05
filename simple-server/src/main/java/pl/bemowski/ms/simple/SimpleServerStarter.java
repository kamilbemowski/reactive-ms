package pl.bemowski.ms.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;

public class SimpleServerStarter extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(SimpleServerStarter.class);

    @Override
    public void start() {
        ServiceDiscovery serviceDiscovery = ServiceDiscovery.create(vertx);
        Record record = HttpEndpoint.createRecord("helloworld", "localhost", 5122, "/");
        serviceDiscovery.publish(record, result -> logger.info("Record published" + result.succeeded()));
        Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/helloworld").handler(context -> context.response().end("Hello"));
        vertx.createHttpServer().requestHandler(router).listen(5122);
    }
}
