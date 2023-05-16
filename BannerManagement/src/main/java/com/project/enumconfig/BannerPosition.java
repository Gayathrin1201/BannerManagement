package com.project.enumconfig;
public enum BannerPosition {
    SIZE_1("1st Size"),
    SIZE_2("2nd Size"),
    SIZE_3("3rd size"),
    SIZE_4("4th size");

    private final String value;

    private BannerPosition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}