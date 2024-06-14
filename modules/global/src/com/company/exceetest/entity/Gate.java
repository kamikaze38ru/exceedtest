package com.company.exceetest.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "EXCEEDTEST_GATE", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_EXCEEDTEST_GATE_UNQ", columnNames = {"GATE_NUMBER"})
})
@Entity(name = "exceedtest_Gate")
@Getter
@Setter
public class Gate extends StandardEntity {
    private static final long serialVersionUID = -596979687317058738L;

    @NotNull
    @Column(name = "GATE_NUMBER", length = 5, nullable = false)
    protected String gateNumber;

    @Column(name = "WORKING_TIME_FROM", nullable = false)
    @NotNull
    protected LocalDateTime workingTimeFrom;

    @Column(name = "WORKING_TIME_TO", nullable = false)
    @NotNull
    protected LocalDateTime workingTimeTo;
}