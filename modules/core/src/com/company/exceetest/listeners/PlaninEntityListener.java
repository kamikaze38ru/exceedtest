package com.company.exceetest.listeners;

import com.company.exceetest.entity.Planin;
import com.company.exceetest.entity.PlaninLog;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.listener.AfterUpdateEntityListener;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("exceedtest_PlaninEntityListener")
public class PlaninEntityListener implements BeforeUpdateEntityListener<Planin>, BeforeInsertEntityListener<Planin> {

    @Override
    public void onBeforeInsert(Planin entity, EntityManager entityManager) {
        logChanges(entity, entityManager);
    }

    @Override
    public void onBeforeUpdate(Planin entity, EntityManager entityManager) {
        logChanges(entity, entityManager);
    }

    private void logChanges(Planin entity, EntityManager entityManager) {
        PlaninLog log = new PlaninLog();
        log.setRegistrationNumber(entity.getRegistrationNumber());
        log.setVehicleNumber(entity.getVehicleNumber());
        log.setStatus(entity.getStatus().ordinal());
        log.setState(entity.getState().ordinal());
        log.setChangeDate(new Date());

        entityManager.persist(log);
    }
}
