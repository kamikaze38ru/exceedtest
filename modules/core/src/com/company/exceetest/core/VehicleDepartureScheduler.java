package com.company.exceetest.core;

import com.company.exceetest.entity.Planin;
import com.company.exceetest.entity.Status;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class VehicleDepartureScheduler {

    @Inject
    private DataManager dataManager;

    @Inject
    private TimeSource timeSource;

    @Scheduled(cron = "0 0 10-18 * * ?")
    public void checkVehicleDeparture() {
        List<Planin> planins = dataManager.load(Planin.class)
                .query("select e from exceedtest_Planin e where e.status = :status and e.exitAllowedDate <= :cutoffTime")
                .parameter("status", Status.DEPARTURE_APPROVED)
                .parameter("cutoffTime", timeSource.now().minusMinutes(getVehicleDepartureTime()))
                .list();

        for (Planin planin : planins) {
            planin.setStatus(Status.DEPARTED);
            planin.setState(null);
            planin.setDepartureDate(timeSource.now().toLocalDateTime());
            planin.setGateNumber("EXIT");
            dataManager.commit(planin);
        }
    }

    private int getVehicleDepartureTime() {
        String value = dataManager.loadValue("select e.value from exceetest_AppSetting e where e.name = :name", String.class)
                .parameter("name", "vehicleDepartureTime")
                .one();
        return Integer.parseInt(value);
    }
}
