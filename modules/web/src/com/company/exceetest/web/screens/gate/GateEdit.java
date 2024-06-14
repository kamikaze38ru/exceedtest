package com.company.exceetest.web.screens.gate;

import com.company.exceetest.entity.Client;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.ValidationException;
import com.haulmont.cuba.gui.screen.*;
import com.company.exceetest.entity.Gate;

import javax.inject.Inject;
import java.util.List;

@UiController("exceedtest_Gate.edit")
@UiDescriptor("gate-edit.xml")
@EditedEntityContainer("gateDc")
@LoadDataBeforeShow
public class GateEdit extends StandardEditor<Gate> {
    @Inject
    private DataManager dataManager;

    @Subscribe
    protected void onInitEntity(InitEntityEvent<Gate> event) {
        Gate gate = event.getEntity();
        List<Gate> existingClients = dataManager.load(Gate.class)
                .query("select e from exceedtest_Gate e where e.gateNumber = :gateNumber")
                .parameter("gateNumber", gate.getGateNumber())
                .list();

        if (!existingClients.isEmpty()) {
            throw new ValidationException("GateNumber code must be unique.");
        }
    }
}