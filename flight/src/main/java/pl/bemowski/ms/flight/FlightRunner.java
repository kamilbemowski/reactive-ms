package pl.bemowski.ms.flight;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class FlightRunner extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(FlightRunner.class);

    @Override
    public void start() {
        logger.info("Start Flight runner");
    }
}
