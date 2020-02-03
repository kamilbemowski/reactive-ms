package pl.bemowski.ms.schedule.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.FlightEvent;
import pl.bemowski.ms.database.schedule.ScheduleStorage;

public class ScheduleHandler {
    private final ScheduleStorage scheduleStorage;
    private final Logger logger = LoggerFactory.getLogger(ScheduleHandler.class);

    public ScheduleHandler(ScheduleStorage scheduleStorage) {
        this.scheduleStorage = scheduleStorage;
    }

    public void handle(Message<String> message) {
        logger.info("Received schedule request");
        FlightEvent flightEvent = Json.decodeValue(message.body(), FlightEvent.class);
        int size = scheduleStorage.check(flightEvent.getDate());
        if (size >= 10) {
            logger.info("Schedule request failed");
            message.fail(200, "No slots available");
        } else {
            scheduleStorage.save(flightEvent);
            message.reply("Flight added to schedule");
        }

    }
}
