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

}
