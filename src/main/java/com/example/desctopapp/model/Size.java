package com.example.desctopapp.model;

public enum Size {
    MB1("1 mb"),
    MB10("10 mb"),
    MB50("50 mb");
    final String size;

    Size(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
