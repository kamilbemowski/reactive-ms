package pl.bemowski.ms.gateway;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;
import pl.bemowski.ms.gateway.request.RequestDispatcher;

public class GatewayStarter extends AbstractVerticle {

    private ServiceDiscovery discovery;
    private CircuitBreaker circuitBreaker;

    @Override
    public void start(Promise<Void> startPromise) {
        discovery = ServiceDiscovery.create(vertx);

        // init circuit breaker instance
        circuitBreaker = CircuitBreaker.create("circuit-breaker", vertx,
                new CircuitBreakerOptions()
                        .setMaxFailures(5)
                        .setTimeout(10000L)
                        .setFallbackOnFailure(true)
                        .setResetTimeout(30000L));

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route("/*").handler(context ->
                discovery.getRecord(fun -> fun.getName().equals(context.request().path().substring(1)),
                        records -> {
                            Record record = records.result();
                            if (record == null) {
                                context.response().setStatusCode(404).end("Resource not found");
                            } else {
                                ServiceReference reference = discovery.getReference(record);
                                HttpClient client = reference.getAs(HttpClient.class);
                                new RequestDispatcher().dispatchRequest(client, context, circuitBreaker, discovery);
                            }
                        }));
        vertx.createHttpServer().requestHandler(router).listen(8123);
        startPromise.complete();
    }

}
