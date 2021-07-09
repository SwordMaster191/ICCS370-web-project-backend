package io.muzoo.ssc.project.backend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name= "tbl_user")//TODO: Link to correct name. Hostname changeable by going to java/resources/application.properties
//Will also need to configure Data source in IntelliJ
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)

    private String username;

    private String password;

    private String group;
}
