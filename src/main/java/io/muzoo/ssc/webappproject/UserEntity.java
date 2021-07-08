package io.muzoo.ssc.webappproject;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntity {

    @Autowired
    private UserRepository userRepo;

    private UserEntity user;

    public UserEntity(UserEntity user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String username;
    private String password;
    private String email;

}
