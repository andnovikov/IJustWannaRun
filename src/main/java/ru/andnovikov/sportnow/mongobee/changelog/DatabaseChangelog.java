package ru.andnovikov.sportnow.mongobee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.andnovikov.sportnow.domain.Distance;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.domain.UserRegistration;
import ru.andnovikov.sportnow.domain.enumeration.EventKind;
import ru.andnovikov.sportnow.domain.enumeration.EventStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "init", author = "andnovikov")
    public void init (MongoTemplate mongoTemplate) throws ParseException {
        User user = null;
        Event event, event1, event2 = null;
        Distance distance = null;
        UserRegistration userRegistration = null;

        user = new User();
        user.setLogin("test");
        user.setPassword("123456");
        user.setFirstName("Testname");
        user.setLastName("Testlastname");
        user.setEmail("test@mail.ru");
        user.setPhone("+78002000600");
        user.setLangKey("ru");

        event1 = new Event();
        event1.setName("Зимний полумарафон");
        event1.setKind(EventKind.RUNNING);
        event1.setDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200119" ));
        event1.setRegStart(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200101" ));
        event1.setRegEnd(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200113" ));
        event1.setStatus(EventStatus.OPEN);
        event1.setUrl("");
        event1.setCountry("Россия");
        event1.setCity("Москва");
        event1.setShortTitle("Просто зимний забег. Полумарафон сам себя не пробежит.");
        distance = new Distance("Зимний полумарафон", 21.1, 200);
        event1.addDistance(distance);
        mongoTemplate.save(distance);
        mongoTemplate.save(event1);

        event2 = new Event();
        event2.setName("Забег в валенках");
        event2.setKind(EventKind.RUNNING);
        event2.setDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200126" ));
        event2.setRegStart(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200101" ));
        event2.setRegEnd(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200126" ));
        event2.setStatus(EventStatus.OPEN);
        event2.setUrl("");
        event2.setCountry("Россия");
        event2.setCity("Пермь");
        event2.setShortTitle("Прими участие! Стань частью спортивной истории Пермского края!");
        distance = new Distance("Забег в валенках", 15, 200);
        event2.addDistance(distance);
        mongoTemplate.save(distance);
        mongoTemplate.save(event2);

        event = new Event();
        event.setName("Забег «Апрель»");
        event.setKind(EventKind.RUNNING);
        event.setDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200305" ));
        event.setRegStart(new SimpleDateFormat( "yyyyMMdd" ).parse( "20190901" ));
        event.setRegEnd(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200207" ));
        event.setStatus(EventStatus.OPEN);
        event.setUrl("https://aprilrun5km.runc.run");
        event.setCountry("Россия");
        event.setCity("Москва");
        event.setShortTitle("Масштабное открытие бегового сезона в Москве. Проверьте себя на дистанции 5 км после зимних тренировок, встретьтесь с друзьями и обновите личный рекорд.");
        distance = new Distance("Забег «Апрель»", 5, 2000);
        event.addDistance(distance);
        mongoTemplate.save(distance);
        mongoTemplate.save(event);

        userRegistration = new UserRegistration();
        userRegistration.setEvent(event1);
        userRegistration.setRegDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200101" ));
        userRegistration.setRegNumber(1);
        user.addRegistration(userRegistration);

        userRegistration = new UserRegistration();
        userRegistration.setEvent(event2);
        userRegistration.setRegDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200101" ));
        userRegistration.setRegNumber(17);
        user.addRegistration(userRegistration);
        mongoTemplate.save(user);
    }

}
