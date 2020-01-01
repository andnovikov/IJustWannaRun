package ru.andnovikov.ijustwannarun.domain.enumeration;

public enum EventKind {
    RUNNING, SWIMMING, TRIATHLON, BIKE, SKIING;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
