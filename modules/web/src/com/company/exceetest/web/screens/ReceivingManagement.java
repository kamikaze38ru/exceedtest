package com.company.exceetest.web.screens;

import com.company.exceetest.entity.*;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.app.core.inputdialog.InputParameter;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.apache.commons.lang3.time.DurationFormatUtils;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@UiController("exceedtest_ReceivingManagement")
@UiDescriptor("ReceivingManagement.xml")
public class ReceivingManagement extends Screen {
    @Inject
    private GroupTable<Planin> scheduledPlaninsTable;
    @Inject
    private Label<String> scheduledCountLabel;
    @Inject
    private Timer refreshTimer;
    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;
    @Inject
    private CollectionLoader<Planin> planinsDl;
    @Inject
    private Notifications notifications;
    @Inject
    private TimeSource timeSource;
    @Inject
    private Dialogs dialogs;
    @Inject
    private Label<String> atGateCountLabel;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private GroupTable<Planin> atGatePlaninsTable;

    @Subscribe
    protected void onInit(InitEvent event) {
        updateScheduledCount();
        updateAtGateCount();
        refreshTimer.addTimerActionListener(timer -> {
            updateScheduledCount();
            refreshScheduledPlanins();
            updateAtGateCount();
            refreshAtGatePlanins();

        });
    }

    private void updateScheduledCount() {
        LoadContext<Planin> loadContext = LoadContext.create(Planin.class)
                .setQuery(LoadContext.createQuery("select e from exceedtest_Planin e "));

        List<Planin> planins = dataManager.loadList(loadContext);
//        Integer count = dataManager.loadValue("select count(e) from exceedtest_Planin e where (e.status=:en1 or e.status=:en2)", Integer.class)
//                .parameter("en1", 10)
//                .parameter("en2", 20)
//                .optional()
//                .orElse(0);


        scheduledCountLabel.setValue("Запланировано: " + planins.size());
    }

    private void refreshScheduledPlanins() {
        planinsDl.load();
    }

    private String generateRegistrationNumber() {
        // Генерация уникального номера регистрации (например, UUID)
        return UUID.randomUUID().toString().substring(0, 10);
    }

    private void updateAtGateCount() {
        LoadContext<Planin> loadContext = LoadContext.create(Planin.class)
                .setQuery(LoadContext.createQuery("select e from exceedtest_Planin e "));
//                        .setParameter("status", Status.AT_GATE)
//                        .setParameter("state", State.GATE_ASSIGNED));
        List<Planin> planins = dataManager.loadList(loadContext);
        atGateCountLabel.setValue("На воротах: " + planins.size());
    }

    private void refreshAtGatePlanins() {
        planinsDl.load();
    }

    @Install(to = "atGatePlaninsTable.timeAtGate", subject = "columnGenerator")
    private Component generateTimeAtGateColumn(Planin planin) {
        Label<String> label = componentsFactory.createComponent(Label.NAME);
        if (planin.getGateAssignmentDate() != null) {
            long minutes = ChronoUnit.MINUTES.between(planin.getGateAssignmentDate(), LocalDateTime.now());
            String duration = DurationFormatUtils.formatDuration(minutes * 60000, "HH:mm");
            label.setValue(duration);
        } else {
            label.setValue("N/A");
        }
        return label;
    }


    public void onRegisterBtnClick() {
        Planin selected = scheduledPlaninsTable.getSingleSelected();
        if (selected != null) {
            dialogs.createInputDialog(this)
                    .withCaption("Регистрация")
                    .withParameters(
                            InputParameter.stringParameter("vehicleNumber").withCaption("Номер ТС").withRequired(true),
                            InputParameter.stringParameter("fullName").withCaption("ФИО").withRequired(true),
                            InputParameter.stringParameter("phone").withCaption("Телефон").withRequired(true),
                            InputParameter.enumParameter("capacity", Capacity.class).withCaption("Грузоподъемность").withRequired(true)
                    )
                    .withCloseListener(closeEvent -> {
                        if (closeEvent.getCloseAction() ==InputDialog.INPUT_DIALOG_OK_ACTION) {
                            selected.setVehicleNumber(closeEvent.getValue("vehicleNumber"));
                            selected.setFullName(closeEvent.getValue("fullName"));
                            selected.setPhone(closeEvent.getValue("phone"));
                            selected.setCapacity(closeEvent.getValue("capacity"));
                            selected.setRegistrationNumber(generateRegistrationNumber());
                            selected.setStatus(Status.REGISTERED);
                            selected.setState(State.AWAITING_GATE_ASSIGNMENT);
                            selected.setRegistrationDate(timeSource.now().toLocalDateTime());

                            dataManager.commit(selected);
                            notifications.create().withCaption("Зарегистрировано").show();
                            updateScheduledCount();
                            refreshScheduledPlanins();
                        }
                    })
                    .show();
        } else {
            notifications.create().withCaption("Выберите строку для регистрации").show();
        }
    }

    public void onScheduledPlaninsTableAssignGateBtnClick() {
        Planin selected = scheduledPlaninsTable.getSingleSelected();
        if (selected != null && selected.getStatus() == Status.REGISTERED) {
            dialogs.createInputDialog(this)
                    .withCaption("Назначить ворота")
                    .withParameters(
                            InputParameter.entityParameter("gate", Gate.class).withCaption("Ворота").withRequired(true)
                    )
                    .withCloseListener(closeEvent -> {
                        if (closeEvent.getCloseAction() == InputDialog.INPUT_DIALOG_OK_ACTION) {
                            Gate gate = closeEvent.getValue("gate");
                            selected.setGateNumber(gate.getGateNumber());
                            selected.setStatus(Status.AT_GATE);
                            selected.setState(State.AT_GATE);
                            selected.setGateAssignmentDate(timeSource.now().toLocalDateTime());

                            dataManager.commit(selected);
                            notifications.create().withCaption("Ворота назначены").show();
                            updateScheduledCount();
                            refreshScheduledPlanins();
                        }
                    })
                    .show();
        } else {
            notifications.create().withCaption("Ворота не могут быть назначены").show();
        }
    }

    public void onCompleteLoadingBtnClick() {
        Planin selected = atGatePlaninsTable.getSingleSelected();
        if (selected != null) {
            selected.setStatus(Status.DEPARTURE_APPROVED);
            selected.setState(State.DEPARTURE_APPROVED);
            selected.setDepartureApprovedDate(timeSource.now().toLocalDateTime());
            selected.setGateNumber("PARKINGEXIT");

            dataManager.commit(selected);
            notifications.create().withCaption("Выезд разрешен").show();
            updateAtGateCount();
            refreshAtGatePlanins();
        } else {
            notifications.create().withCaption("Выберите строку для завершения загрузки").show();
        }
    }
}