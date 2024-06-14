package com.company.exceetest.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Table(name = "EXCEEDTEST_CLIENT", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_EXCEEDTEST_CLIENT_UNQ", columnNames = {"CLIENT_CODE"})
})
@Entity(name = "exceedtest_Client")
@Getter
@Setter
public class Client extends StandardEntity {
    private static final long serialVersionUID = -5545332870542986747L;

    @NotNull
    @Column(name = "CLIENT_CODE", nullable = false)
    protected Integer clientCode;

    @NotNull
    @Column(name = "NAME", length = 200, nullable = false)
    protected String name;

    @Column(name = "ADDRESS", length = 200)
    protected String address;

    @Column(name = "CONTACT_PERSON", length = 100)
    protected String contactPerson;
}