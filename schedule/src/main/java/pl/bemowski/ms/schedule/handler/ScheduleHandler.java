package pl.bemowski.ms.schedule.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import pl.bemowski.ms.common.model.FlightEvent;
import pl.bemowski.ms.database.schedule.ScheduleStorage;

public class ScheduleHandler {
    private final ScheduleStorage scheduleStorage;

    public ScheduleHandler(ScheduleStorage scheduleStorage) {
        this.scheduleStorage = scheduleStorage;
    }

    public void handle(Message<String> message) {
        FlightEvent flightEvent = Json.decodeValue(message.body(), FlightEvent.class);
        int size = scheduleStorage.check(flightEvent.getDate());
        if (size >= 10) {
            message.fail(200, "Failed");
        } else {
            scheduleStorage.save(flightEvent);
        }

    }
}
