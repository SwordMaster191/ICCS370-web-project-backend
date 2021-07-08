package io.muzoo.ssc.webappproject.Servlets;

import io.muzoo.ssc.webappproject.SecurityService;
import io.muzoo.ssc.webappproject.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class HomeServlet {

    @Autowired
    private UserRepository userRepo;

    SecurityService security;

    @GetMapping(value = "/home")
    public String getHome() {
        return "home.jsp";
    }

}
