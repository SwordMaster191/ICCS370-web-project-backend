package io.muzoo.ssc.project.backend.whoami;
//A controller to get currently logged in user

import io.muzoo.ssc.project.backend.User;
import io.muzoo.ssc.project.backend.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhoamIController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/whoami")
    public WhoamiDTO whoami(){
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal != null && principal instanceof org.springframework.security.core.userdetails.User){
                //If user is logged in
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;

                User u = userRepository.findFirstByUsername(user.getUsername());

                return WhoamiDTO.builder().loggedIn(true).name(u.getUsername()).role(u.getRole()).username(u.getUsername()).build();
            }
        }
        catch (Exception e){

        }
        return WhoamiDTO.builder().loggedIn(false).build();
    }
}
