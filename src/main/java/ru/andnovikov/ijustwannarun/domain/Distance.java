package ru.andnovikov.ijustwannarun.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("distances")
public class Distance {

    @Id
    private String id;
    // distance name
    private String name;
    // diastance length in km
    private float length;
    // participants limit
    private int limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", length=" + getLength() +
                ", limit=" + getLimit() +
                "}";
    }
}
