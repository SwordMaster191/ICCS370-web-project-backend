package io.muzoo.ssc.webappproject;

import io.muzoo.ssc.webappproject.User.User;
import io.muzoo.ssc.webappproject.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SecurityService {

    @Autowired
    private UserRepository userRepo;



    public boolean isAuthorized(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String username = (String) request.getSession().getAttribute("username");
        return (username != null);
    }

    public Boolean authenticate(String username, String password) throws SQLException, ClassNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            new User();
            return true;
        } else {
            return false;
        }
    }

}
