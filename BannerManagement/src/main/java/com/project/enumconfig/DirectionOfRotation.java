package com.project.enumconfig;



public enum DirectionOfRotation {
    RIGHT_TO_LEFT("Right to Left"),
    LEFT_TO_RIGHT("Left to Right");

    private final String value;

    private DirectionOfRotation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
