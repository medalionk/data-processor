package ee.bilal.dev.dataprocessor.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Controller
public class HomeController {

    /**
     * Redirect to Swagger Api documentations
     * @return
     */
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }

}
