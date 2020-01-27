package ru.andnovikov.sportnow.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum RegStatus {
    NEW("new"),
    PAYED("payed");

    private String name;

    RegStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
