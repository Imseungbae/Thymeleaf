package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @Data
    public static class User {
        private String userName;
        private int age;

        public User(String userName, int age){
            this.userName = userName;
            this.age = age;
        }
    }

    @GetMapping("text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring~!");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescapped(Model model){
        model.addAttribute("data", "<b>Hello</b> Spring~!");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> userList = new ArrayList<User>();
        userList.add(userA);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);

        model.addAttribute("user", userA);
        model.addAttribute("users", userList);
        model.addAttribute("userMap", map);
        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession httpSession){
        httpSession.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data){
            return "Hello" + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2","data2");
        return "basic/link";
    }

    @GetMapping("literal")
    public String literal(Model model) {
        model.addAttribute("data", "data1");
        return "basic/literal";
    }

    @GetMapping("attribute")
    public String attribute(Model model) {
        model.addAttribute("data", "spring");
        return "basic/attribute";
    }

    @GetMapping("operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

}
