package pl.bemowski.ms.schedule;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.common.model.EventBusAddresses;
import pl.bemowski.ms.database.schedule.ScheduleStorage;
import pl.bemowski.ms.schedule.handler.ScheduleHandler;

public class ScheduleRunner extends AbstractVerticle {

    @Override
    public void start() {
        ScheduleHandler scheduleHandler = new ScheduleHandler(new ScheduleStorage());
        vertx.eventBus().consumer(EventBusAddresses.SCHEDULE, scheduleHandler::handle);
    }
}
