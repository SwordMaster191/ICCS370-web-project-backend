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

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "date")
    private Date date;

}
