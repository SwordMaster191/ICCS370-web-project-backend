package io.muzoo.ssc.webappproject.Servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeServlet {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome() {
        return "home.jsp";
    }

}
