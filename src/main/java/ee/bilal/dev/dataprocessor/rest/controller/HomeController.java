package ee.bilal.dev.dataprocessor.rest.controller;

import ee.bilal.dev.dataprocessor.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Controller
public class HomeController {
    @Loggable
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
