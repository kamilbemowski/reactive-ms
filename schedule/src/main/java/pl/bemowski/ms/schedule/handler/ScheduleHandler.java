package pl.bemowski.ms.schedule.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.database.schedule.ScheduleStorage;

public class ScheduleHandler {
    private final ScheduleStorage scheduleStorage;
    private final Logger logger = LoggerFactory.getLogger(ScheduleHandler.class);

    public ScheduleHandler(ScheduleStorage scheduleStorage) {
        this.scheduleStorage = scheduleStorage;
    }

    public void handle(Message<String> message) {


    }
}
