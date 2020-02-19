package pl.bemowski.ms.gateway.request;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.ServiceDiscovery;

public class RequestDispatcher {

    private Logger logger = LoggerFactory.getLogger(RequestDispatcher.class);

    public void dispatchRequest(HttpClient client, RoutingContext context, CircuitBreaker circuitBreaker, ServiceDiscovery discovery) {
        logger.info("CB state:" + circuitBreaker.state());
        circuitBreaker.execute(promise -> {
            HttpClientRequest toReq = client
                    .request(context.request().method(), context.request().uri(), response ->
                            response.bodyHandler(body -> {
                                if (response.statusCode() >= 500) { // api endpoint server error, circuit breaker should fail
                                    logger.warn("Request failed");
                                    promise.fail(response.statusCode() + ": " + body.toString());
                                    context.response().end("Failed");
                                } else {
                                    HttpServerResponse toRsp = context.response()
                                            .setStatusCode(response.statusCode());
                                    response.headers().forEach(header ->
                                            toRsp.putHeader(header.getKey(), header.getValue()));
                                    // send response
                                    toRsp.end(body);
                                    promise.complete();
                                }
                                ServiceDiscovery.releaseServiceObject(discovery, client);
                            }));
            // set headers
            context.request().headers().forEach(header -> toReq.putHeader(header.getKey(), header.getValue()));
            // send request
            if (context.getBody() == null || context.getBody().length() == 0) {
                toReq.end();
            } else {
                toReq.end(context.getBody());
            }
        }).onFailure(throwable -> {
//            logger.error(throwable.getMessage());
            context.response().setStatusCode(404);
        }).onComplete(handler -> {
            context.response().setStatusCode(403).end("Closed");
        });
    }
}
