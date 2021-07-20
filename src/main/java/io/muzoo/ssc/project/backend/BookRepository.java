package io.muzoo.ssc.project.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByUsername(String username);
    // for current user's reservation

//    select date from table where startTime is in between startTime and endTime
//            if exist cannot reserve

    Book findFirstByDateAndStartTimeBetween(Date date, Time startTime, Time endTime);

//    List<Book> findByDateAndStartTimeBetween(Date date, Time startTime, Time endTime);

    List<Book> findByDate(Date date);

    void deleteByDateAndStartTimeAndEndTime(Date date, Time startTime, Time endTime);

}
