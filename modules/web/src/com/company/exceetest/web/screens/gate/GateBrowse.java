package com.company.exceetest.web.screens.gate;

import com.haulmont.cuba.gui.screen.*;
import com.company.exceetest.entity.Gate;

@UiController("exceedtest_Gate.browse")
@UiDescriptor("gate-browse.xml")
@LookupComponent("gatesTable")
@LoadDataBeforeShow
public class GateBrowse extends StandardLookup<Gate> {
}