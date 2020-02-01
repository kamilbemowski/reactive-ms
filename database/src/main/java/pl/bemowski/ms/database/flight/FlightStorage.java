package pl.bemowski.ms.database.flight;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import pl.bemowski.ms.common.model.FlightEvent;

public class FlightStorage {
    private final IMap<String, FlightEvent> airlineMap;

    public FlightStorage() {
        airlineMap = Hazelcast.getAllHazelcastInstances().iterator().next().getMap("flightMap");
    }

    public void save(FlightEvent flightEvent) {
        airlineMap.set(flightEvent.key(), flightEvent);
    }
}
