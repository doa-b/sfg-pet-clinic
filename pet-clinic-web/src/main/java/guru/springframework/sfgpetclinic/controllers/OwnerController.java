package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Doa on 23-5-2019.
 */
@RequestMapping("owners")
@Controller
public class OwnerController {

//    @RequestMapping({"/owners", "/owners/index", "owners/index.html"})
    @RequestMapping({"", "/", "/index", "index.html"})
    public String listOwners(){
        return "owners/index";
    }
}
