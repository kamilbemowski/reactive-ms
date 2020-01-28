package pl.bemowski.ms.database.airline;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import pl.bemowski.ms.common.model.AirlineEvent;

public class AirlineStorage {

    private final IMap<String, AirlineEvent> airlineMap;

    public AirlineStorage() {
        airlineMap = Hazelcast.getAllHazelcastInstances().iterator().next().getMap("airlineMap");
    }

    public void save(AirlineEvent airlineEvent) {
        airlineMap.set(airlineEvent.key(), airlineEvent);
    }
}
