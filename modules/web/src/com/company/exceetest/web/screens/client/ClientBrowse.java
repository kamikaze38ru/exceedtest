package com.company.exceetest.web.screens.client;

import com.haulmont.cuba.gui.screen.*;
import com.company.exceetest.entity.Client;

@UiController("exceedtest_Client.browse")
@UiDescriptor("client-browse.xml")
@LookupComponent("clientsTable")
@LoadDataBeforeShow
public class ClientBrowse extends StandardLookup<Client> {
}