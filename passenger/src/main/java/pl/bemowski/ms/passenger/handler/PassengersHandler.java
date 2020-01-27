package pl.bemowski.ms.passenger.handler;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLClient;

public class PassengersHandler {
    private final Vertx vertx;
    private Logger logger = LoggerFactory.getLogger(PassengersHandler.class);

    public PassengersHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    public void handle(Message<Object> message) {
        logger.info("Handle message");
        JsonObject config = new JsonObject();
        SQLClient client = JDBCClient.createShared(vertx, config, "jdbc:h2:~/test");
        client.call("Select 1", handler -> {
            logger.info(handler.result());
        });
    }
}
