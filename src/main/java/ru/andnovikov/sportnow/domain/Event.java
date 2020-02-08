package ru.andnovikov.sportnow.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.andnovikov.sportnow.domain.enumeration.EventKind;
import ru.andnovikov.sportnow.domain.enumeration.EventStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    // event name
    @Column(name = "name", nullable = false)
    private String name;
    // sport type
    @Column(name = "kind", nullable = false)
    private EventKind kind;
    // event date
    @Column(name = "date", nullable = false)
    private Date date;
    // when registration starts
    @Column(name = "reg_start", nullable = false)
    private Date regStart;
    // when registration ends
    @Column(name = "reg_end", nullable = false)
    private Date regEnd;
    // event status
    @Column(name = "status", nullable = false)
    private EventStatus status;
    // url to event page
    @Column(name = "url", nullable = false)
    private String url;
    // country
    @Column(name = "country", nullable = false)
    private String country;
    // city
    @Column(name = "city", nullable = false)
    private String city;
    // about
    @Column(name = "short_title", nullable = false)
    private String shortTitle;
    // about
    @Column(name = "title", nullable = false)
    private String title;
    // available distances

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Distance> distances = new ArrayList<>();

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

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addDistance(Distance distance) {
        if (this.distances == null) {
            this.distances = new ArrayList<>();
        }
        this.distances.add(distance);
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
