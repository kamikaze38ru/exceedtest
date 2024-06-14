package com.company.exceetest.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "PlaninLog")
@PublishEntityChangedEvents
@NamePattern("%s %s|registrationNumber,changeDate")
@Entity(name = "exceedtest_PlaninLog")
@Getter
@Setter
public class PlaninLog extends StandardEntity {
    private static final long serialVersionUID = -8932825828973331521L;

    @Column(name = "REGISTRATION_NUMBER", length = 10)
    private String registrationNumber;

    @Column(name = "VEHICLE_NUMBER", length = 20)
    private String vehicleNumber;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "STATE")
    private Integer state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHANGE_DATE")
    private Date changeDate;
}
