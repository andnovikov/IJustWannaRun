package ru.andnovikov.sportnow.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "distances")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    // distance name
    private String name;
    // diastance length in km
    private double length;
    // participants limit
    private int participant_limit;
    // price for registration
    private int cost;

    public Distance (String name, double length, int participant_limit) {
        this.name = name;
        this.length = length;
        this.participant_limit = participant_limit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getParticipant_limit() {
        return participant_limit;
    }

    public void setParticipant_limit(int limit) {
        this.participant_limit = limit;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", length=" + getLength() +
                ", limit=" + getParticipant_limit() +
                "}";
    }
}
