package pl.bemowski.ms.timetable;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.timetable.handler.TimetableHandler;
import pl.bemowski.ms.timetable.printer.TimetablePrinter;

public class TimetableRunner extends AbstractVerticle {

    @Override
    public void start() {
        TimetableHandler timetableHandler = new TimetableHandler(new TimetablePrinter());
        vertx.eventBus().consumer("timetable", timetableHandler::handle);
    }
}
