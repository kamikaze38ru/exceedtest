package com.company.exceetest.web.screens.client;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.ValidationException;
import com.haulmont.cuba.gui.screen.*;
import com.company.exceetest.entity.Client;

import javax.inject.Inject;
import java.util.List;

@UiController("exceedtest_Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
@LoadDataBeforeShow
public class ClientEdit extends StandardEditor<Client> {
    @Inject
    private DataManager dataManager;

    @Subscribe
    protected void onInitEntity(InitEntityEvent<Client> event) {
        Client client = event.getEntity();
        List<Client> existingClients = dataManager.load(Client.class)
                .query("select e from exceedtest_Client e where e.clientCode = :clientCode")
                .parameter("clientCode", client.getClientCode())
                .list();

        if (!existingClients.isEmpty()) {
            throw new ValidationException("Client code must be unique.");
        }
    }
}