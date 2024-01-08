package com.karkar.springboot.enums;

public enum OperatorType {

    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    public final String value;
    private OperatorType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
