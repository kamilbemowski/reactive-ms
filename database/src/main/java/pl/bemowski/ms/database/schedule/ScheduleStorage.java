package pl.bemowski.ms.database.schedule;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import pl.bemowski.ms.common.model.FlightEvent;

import java.time.LocalDate;

public class ScheduleStorage {
    private final HazelcastInstance instance;

    public ScheduleStorage() {
        instance = Hazelcast.getAllHazelcastInstances().iterator().next();
    }

    public int check(LocalDate date) {
        return instance.getMap("schedule" + date.toString()).size();
    }

    public void save(FlightEvent flightEvent) {
        instance.getMap("schedule" + flightEvent.getDate().toString()).put(flightEvent.key(), flightEvent);
    }
}
