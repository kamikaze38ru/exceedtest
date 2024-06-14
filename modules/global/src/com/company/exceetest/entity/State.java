package com.company.exceetest.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum State implements EnumClass<Integer> {

    AWAITING_GATE_ASSIGNMENT(5),
    AT_GATE(20),
    DEPARTURE_APPROVED(50);

    private Integer id;

    State(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static State fromId(Integer id) {
        for (State at : State.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}