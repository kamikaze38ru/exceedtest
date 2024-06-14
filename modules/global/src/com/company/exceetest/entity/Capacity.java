package com.company.exceetest.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Capacity implements EnumClass<String> {

    GAZEL("1"),
    TRUCK("2");

    private String id;

    Capacity(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Capacity fromId(String id) {
        for (Capacity at : Capacity.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}