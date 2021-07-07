package io.muzoo.ssc.webappproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findById(int id);

    void addNewUser(int id, String username, String password, String email);

}