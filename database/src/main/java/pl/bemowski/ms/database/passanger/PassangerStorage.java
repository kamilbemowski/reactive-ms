package pl.bemowski.ms.database.passanger;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import pl.bemowski.ms.common.model.PassangerEvent;

public class PassangerStorage {
    private final HazelcastInstance hazelcast;

    public PassangerStorage() {
        this.hazelcast = Hazelcast.getAllHazelcastInstances().iterator().next();
    }

    public void save(PassangerEvent event) {
        IMap<String, PassangerEvent> passengers = hazelcast.getMap("passengers");
        passengers.set(event.getKey(), event);
    }
}
