package com.company.exceetest.web.screens.planinlog;

import com.haulmont.cuba.gui.screen.*;
import com.company.exceetest.entity.PlaninLog;

@UiController("exceedtest_PlaninLog.browse")
@UiDescriptor("planin-log-browse.xml")
@LookupComponent("planinLogsTable")
@LoadDataBeforeShow
public class PlaninLogBrowse extends StandardLookup<PlaninLog> {
}