package pl.bemowski.ms.database.passanger;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.PassengerEvent;

public class PassangerStorage {
    private final HazelcastInstance hazelcast;
    private Logger logger = LoggerFactory.getLogger(PassangerStorage.class);

    public PassangerStorage() {
        this.hazelcast = Hazelcast.getAllHazelcastInstances().iterator().next();
    }

    public void save(PassengerEvent event) {
        logger.info("Save passenger: " + event);
        IMap<String, PassengerEvent> passengers = hazelcast.getMap("passengers");
        passengers.set(event.key(), event);
    }
}
