package com.company.exceetest.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Status implements EnumClass<Integer> {

    PLANNED(10),
    REGISTERED(20),
    AT_GATE(30),
    DEPARTURE_APPROVED(50),
    DEPARTED(90);

    private Integer id;

    Status(int value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Status fromId(Integer id) {
        for (Status at : Status.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}