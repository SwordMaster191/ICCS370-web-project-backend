package io.muzoo.ssc.webappproject.Servlets;

import io.muzoo.ssc.webappproject.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginServlet {

    @GetMapping(value = "/login")
    public String getLogin(ModelMap model){
        return "login.jsp";
    }


}
