package io.muzoo.ssc.webappproject.Servlets;

import io.muzoo.ssc.webappproject.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

public class LoginServlet {

    @Autowired
    SecurityService security;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(ModelMap model, @RequestParam String username, @RequestParam String password) throws SQLException, ClassNotFoundException {

        boolean isValidUser = security.authenticate(username, password);

        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }

        model.put("username", username);
        model.put("password", password);

        return "welcome";
    }


}
