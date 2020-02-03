package pl.bemowski.ms.database.passanger;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.MultiMap;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.PassengerEvent;

public class PassangerStorage {
    private Logger logger = LoggerFactory.getLogger(PassangerStorage.class);
    private MultiMap<String, PassengerEvent> passengers;

    public PassangerStorage() {
        passengers = Hazelcast.getAllHazelcastInstances().iterator().next().getMultiMap("passengers");
    }

    public void save(PassengerEvent event) {
        logger.info("Save passenger: " + event);
        passengers.put(event.key(), event);
    }

}
