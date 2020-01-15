package ru.andnovikov.sportnow.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.andnovikov.sportnow.domain.enumeration.RegStatus;

import java.util.Date;

@Document(collection = "registrations")
public class Registration {

    @Id
    private String id;
    @DBRef
    private Event event;
    @Field("reg_date")
    private Date regDate;
    @Field("reg_status")
    private RegStatus status;
    @Field("reg_number")
    private Integer regNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Integer regNumber) {
        this.regNumber = regNumber;
    }

    public RegStatus getStatus() {
        return status;
    }

    public void setStatus(RegStatus status) {
        this.status = status;
    }
}
