package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.User;
import hello.UserRepository;

@Controller
@RequestMapping(path="/users")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/update")
    public @ResponseBody String removeUser (@RequestParam Integer userId, @RequestParam String name, @RequestParam String email) {
        User n = userRepository.findById(userId).get();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Updated";
    }

    @GetMapping(path="/delete")
    public @ResponseBody String removeUser (@RequestParam Integer userId) {
        User n = userRepository.findById(userId).get();
        userRepository.delete(n);
        return "Deleted";
    }
}