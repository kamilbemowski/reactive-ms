package pl.bemowski.ms.passenger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;
import pl.bemowski.ms.common.model.EventBusAddresses;
import pl.bemowski.ms.common.model.PassengerEvent;
import pl.bemowski.ms.database.passanger.PassangerStorage;
import pl.bemowski.ms.passenger.handler.PassengersHandler;

import java.util.Collection;

public class PassengerStarter extends AbstractVerticle {

    private PassangerStorage passengerStorage;

    @Override
    public void start() {
        passengerStorage = new PassangerStorage();
        PassengersHandler passengersHandler = new PassengersHandler(passengerStorage);
        vertx.eventBus()
                .consumer(EventBusAddresses.PASSENGERS, passengersHandler::handle);
        startHttpServer();
    }

    private void startHttpServer() {
        int port = 5001;
        Record record = HttpEndpoint.createRecord("passengers", "localhost", port, "/");
        ServiceDiscovery discovery = ServiceDiscovery.create(vertx);
        discovery.publish(record, ar -> {
        });
        Router router = Router.router(vertx);
        BodyHandler handler = BodyHandler.create();
        router.route().handler(handler);
        router.get("/passengers").handler(context -> {
            HttpServerRequest request = context.request();
            Collection<PassengerEvent> passengerEvents =
                    passengerStorage.getAllByFlightNumberAndDate(request.getParam("flightNumber"),
                            request.getParam("date"));
            context.response().setStatusCode(200).end(Json.encode(passengerEvents));
        });
        vertx.createHttpServer().requestHandler(router).listen(port);
    }
}
