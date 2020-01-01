package ru.andnovikov.ijustwannarun.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.andnovikov.ijustwannarun.domain.enumeration.EventKind;
import ru.andnovikov.ijustwannarun.domain.enumeration.EventStatus;

import java.util.Date;
import java.util.List;

@Document("events")
public class Event {

    @Id
    private String id;
    // event name
    private String name;
    // sport type
    private EventKind kind;
    // event date
    private Date date;
    // when registration starts
    @Field("reg_start")
    private Date regStart;
    // when registration ends
    @Field("reg_date")
    private Date regEnd;
    // event status
    private EventStatus status;
    // url to event page
    private String url;
    // country
    private String country;
    // city
    private String city;
    // available distances
    private List<Distance> distances;

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

    public EventKind getKind() {
        return kind;
    }

    public void setKind(EventKind kind) {
        this.kind = kind;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRegStart() {
        return regStart;
    }

    public void setRegStart(Date regStart) {
        this.regStart = regStart;
    }

    public Date getRegEnd() {
        return regEnd;
    }

    public void setRegEnd(Date regEnd) {
        this.regEnd = regEnd;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", kind='" + getKind() + "'" +
                ", date='" + getDate() + "'" +
                ", reg_start='" + getRegStart() + "'" +
                ", reg_end='" + getRegEnd() + "'" +
                ", status='" + getStatus() + "'" +
                ", url='" + getUrl() + "'" +
                ", country='" + getKind() + "'" +
                ", city='" + getKind() + "'" +
                "}";
    }
}
