package pl.bemowski.ms.database;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.bemowski.ms.common.model.PassangerEvent;

import java.util.Set;

public class Runner extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(Runner.class);

    @Override
    public void start(Promise<Void> fut) {
        logger.info("Start");
        Set<HazelcastInstance> allHazelcastInstances = Hazelcast.getAllHazelcastInstances();
        HazelcastInstance next = allHazelcastInstances.iterator().next();
        IMap<Long, PassangerEvent> passangers = next.getMap("passangers");
        passangers.put(1L, new PassangerEvent());
        logger.info("Passengers maps: " + passangers.size());
        fut.fail("End");
    }
}