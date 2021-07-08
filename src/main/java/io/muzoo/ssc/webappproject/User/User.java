package io.muzoo.ssc.webappproject.User;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 999)
    private String name;

    @Column(name = "username", nullable = false, length = 999)
    private String username;

    @Column(nullable = false, length = 999)
    private String password;

    @Column(nullable = false, unique = true, length = 999)
    private String email;

}
