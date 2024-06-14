package com.company.exceetest.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "exceetest_AppSetting")
@Table(name = "EXCEETEST_APP_SETTING")
public class AppSetting extends StandardEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "VALUE_", nullable = false)
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
