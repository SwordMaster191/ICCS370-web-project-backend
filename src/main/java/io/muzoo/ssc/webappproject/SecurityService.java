package io.muzoo.ssc.webappproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepo;

    public boolean isAuthorized(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String username = (String) request.getSession().getAttribute("username");
        return (username != null);
    }

    public Boolean authenticate(String username, String password) throws SQLException, ClassNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user != null) {
            new UserEntity(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean usernameExist(String username) {
        UserEntity user = userRepo.findByUsername(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean emailExist(String email) {
        UserEntity user = userRepo.findByEmail(email);
        if (user != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean idExist(int id) {
        UserEntity user = userRepo.findById(id);
        if (user != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addNewUser(int id, String username, String password, String email) {
        userRepo.addNewUser(id, username, password, email);
    }

}
