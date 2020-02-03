package pl.bemowski.ms.timetable.handler;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import pl.bemowski.ms.common.model.FlightEvent;
import pl.bemowski.ms.timetable.printer.TimetablePrinter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TimetableHandler {
    private TimetablePrinter timetablePrinter;
    private Map<String, FlightEvent> currentFlights = new HashMap<>();

    public TimetableHandler(TimetablePrinter timetablePrinter) {
        this.timetablePrinter = timetablePrinter;
    }

    public void handle(Message<String> message) {
        FlightEvent flightEvent = Json.decodeValue(message.body(), FlightEvent.class);
        LocalDate flightDate = flightEvent.getDate();
        if (flightDate.isEqual(LocalDate.now())) {
            currentFlights.put(flightEvent.key(), flightEvent);
        }
        timetablePrinter.print(currentFlights.values());
    }
}
