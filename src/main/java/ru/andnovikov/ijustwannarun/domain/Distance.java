package ru.andnovikov.ijustwannarun.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document("distances")
public class Distance {

    @Id
    private String id;
    // distance name
    private String name;
    // diastance length in km
    private double length;
    // participants limit
    private int limit;

    public Distance (String name, double length, int limit) {
        this.name = name;
        this.length = length;
        this.limit = limit;
    }

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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
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
