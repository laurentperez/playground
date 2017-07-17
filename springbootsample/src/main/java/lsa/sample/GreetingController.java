package lsa.sample;

import lsa.sample.domain.User;
import lsa.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class GreetingController {

    private final GreetingService service;

    private final UserService userService;

    public GreetingController(GreetingService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @RequestMapping("/greeting")
    public @ResponseBody String greeting() {
        return service.greet();
    }

    @GetMapping("/users")
    @ResponseBody
    @Transactional(readOnly = true)
    public String loadUsers() {
        Iterable<User> users = this.userService.loadAll();
        return users.toString();
    }

}
