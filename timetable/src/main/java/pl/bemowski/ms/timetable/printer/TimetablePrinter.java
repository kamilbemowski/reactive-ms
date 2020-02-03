package pl.bemowski.ms.timetable.printer;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.FlightEvent;

import java.util.Collection;

public class TimetablePrinter {
    private Logger logger = LoggerFactory.getLogger(TimetablePrinter.class);

    public void print(Collection<FlightEvent> currentFlights) {
        if (!currentFlights.isEmpty()) {
            StringBuilder sb = new StringBuilder("\n---TIMETABLE---\n");
            currentFlights.forEach(line -> sb.append(line).append("\n"));
            sb.append("---------------");
            logger.info(sb.toString());
        }
    }
}
