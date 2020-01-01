package ru.andnovikov.ijustwannarun.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

@Document(collection = "user_registration")
public class UserRegistration {

    @Id
    private String id;
    @Field("reg_date")
    private ZonedDateTime regDate;
    @Field("reg_number")
    private Integer regNumber;

}
