package io.muzoo.ssc.webappproject.Servlets;

import io.muzoo.ssc.webappproject.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

public class LoginServlet {

    @Autowired
    SecurityService security;

    @GetMapping(value = "/login")
    public String getLogin(ModelMap model){
        return "login";
    }

    @PostMapping(value = "/login")
    public String postLogin(ModelMap model, @RequestParam String username, @RequestParam String password) throws SQLException, ClassNotFoundException {

        boolean isValidUser = security.authenticate(username, password);

        if (!isValidUser) {
            model.put("error", "Invalid Credentials");
            return "login";
        }

        model.put("username", username);
        model.put("password", password);

        return "welcome";
    }


}
