package com.company.exceetest.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Listeners("exceedtest_PlaninEntityListener")
@Table(name = "EXCEEDTEST_PLANIN")
@Entity(name = "exceedtest_Planin")
@Getter
@Setter
public class Planin extends StandardEntity {
    private static final long serialVersionUID = 2021423891887088356L;

    @Column(name = "REGISTRATION_NUMBER", length = 10)
    private String registrationNumber;

    @Column(name = "STATE")
    private Integer state;

    @Column(name = "ORDER_NUMBER", length = 50)
    protected String orderNumber;

    @JoinTable(name = "SAMPLE_PLANIN_CLIENT_LINK",
            joinColumns = @JoinColumn(name = "PLANIN_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_ID"))
    @ManyToMany
    protected List<Client> suppliers;

    @Column(name = "PLANNED_ARRIVAL_DATE")
    protected LocalDateTime plannedArrivalDate;

    @Column(name = "VEHICLE_NUMBER", length = 20)
    protected String vehicleNumber;

    @Column(name = "CAPACITY")
    protected String capacity;

    @Column(name = "FULL_NAME", length = 100)
    protected String fullName;

    @Column(name = "PHONE", length = 20)
    protected String phone;

    @Column(name = "STATUS")
    protected Integer status;

    @Column(name = "GATE_NUMBER", length = 5)
    protected String gateNumber;

    @Column(name = "REGISTRATION_DATE")
    protected LocalDateTime registrationDate;

    @Column(name = "GATE_ASSIGNMENT_DATE")
    protected LocalDateTime gateAssignmentDate;

    @Column(name = "DEPARTURE_APPROVED_DATE")
    protected LocalDateTime departureApprovedDate;

    @Column(name = "DEPARTURE_DATE")
    protected LocalDateTime departureDate;

    public State getState() {
        return state == null ? null : State.fromId(state);
    }

    public void setState(State state) {
        this.state = state == null ? null : state.getId();
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity == null ? null : capacity.getId();
    }

    public Capacity getCapacity() {
        return capacity == null ? null : Capacity.fromId(capacity);
    }

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }

    public Status getStatus() {
        return status == null ? null : Status.fromId(status);
    }
}