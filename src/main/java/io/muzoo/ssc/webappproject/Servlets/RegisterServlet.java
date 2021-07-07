package io.muzoo.ssc.webappproject.Servlets;

import io.muzoo.ssc.webappproject.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class RegisterServlet {

    @Autowired
    SecurityService security;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(ModelMap model) { return "register.jsp"; }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void postRegister(ModelMap model, @RequestParam String username, @RequestParam String password,
                               @RequestParam String email, @RequestParam int id) {
        if (security.idExist(id)){
            model.put("idAlreadyExists", "ID already exists.");
            // go to regis page
        }
        if (security.usernameExist(username)) {
            model.put("usernameAlreadyExists", "Username already exists. Please select a new username.");
        }
        if (security.emailExist(email)) {
            model.put("emailAlreadyExists", "Email already exists. Please select a new email.");
        }
        else {
            model.put("registrationSucceeded", "Registration completed!");
        }
        // go to login page
    }

}
