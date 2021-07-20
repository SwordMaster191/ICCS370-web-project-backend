package io.muzoo.ssc.project.backend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Getter
@Setter
@Table(name= "reservation")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookingId;

    @Column(unique = true)
    private String username;

    @Column(name = "startTime")
//    @Temporal(TemporalType.TIME)
    private Time startTime;

    @Column(name = "endTime")
//    @Temporal(TemporalType.TIME)
    private Time endTime;

    @Column(name = "date")
//    @Temporal(TemporalType.DATE)
    private Date date;

}
