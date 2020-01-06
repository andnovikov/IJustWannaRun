package ru.andnovikov.ijustwannarun.mongobee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.andnovikov.ijustwannarun.domain.Distance;
import ru.andnovikov.ijustwannarun.domain.Event;
import ru.andnovikov.ijustwannarun.domain.enumeration.EventKind;
import ru.andnovikov.ijustwannarun.domain.enumeration.EventStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "init", author = "andnovikov")
    public void init (MongoTemplate mongoTemplate) throws ParseException {
        Event event = null;
        Distance distance = null;

        event = new Event();
        event.setName("Зимний полумарафон");
        event.setKind(EventKind.RUNNING);
        event.setDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200119" ));
        event.setRegStart(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200101" ));
        event.setRegEnd(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200113" ));
        event.setStatus(EventStatus.OPEN);
        event.setUrl("");
        event.setCountry("Россия");
        event.setCity("Москва");
        event.setShortTitle("Просто зимний забег. Полумарафон сам себя не пробежит.");
        distance = new Distance("Зимний полумарафон", 21.1, 200);
        event.addDistance(distance);
        mongoTemplate.save(distance);
        mongoTemplate.save(event);

        event = new Event();
        event.setName("Забег в валенках");
        event.setKind(EventKind.RUNNING);
        event.setDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200126" ));
        event.setRegStart(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200101" ));
        event.setRegEnd(new SimpleDateFormat( "yyyyMMdd" ).parse( "20200126" ));
        event.setStatus(EventStatus.OPEN);
        event.setUrl("");
        event.setCountry("Россия");
        event.setCity("Пермь");
        event.setShortTitle("Прими участие! Стань частью спортивной истории Пермского края!");
        distance = new Distance("Забег в валенках", 15, 200);
        event.addDistance(distance);
        mongoTemplate.save(distance);
        mongoTemplate.save(event);

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

        // TODO: save included distances


    }

}
