package io.muzoo.ssc.project.backend.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {
    //EP5 @17:00 minute mark
    @GetMapping("/api/test")
    public String test(){
        return "If this message is shown, it means that login is successful because we did not set to permit this path.";
    }

    @PostMapping("/api/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
            request.login(username, password);
            return "Login successful";
        }
        catch (ServletException e){
            return "Failed to login";
        }

    }

    @GetMapping("/api/logout")
    public String logout(HttpServletRequest request){
        try{
            request.logout();
            return "Logout successful";
        }
        catch (ServletException e){
            return "Failed to logout";
        }
    }
}
