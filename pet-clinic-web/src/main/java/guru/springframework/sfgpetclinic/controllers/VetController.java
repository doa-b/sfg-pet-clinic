package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Doa on 23-5-2019.
 */
@Controller
public class VetController {

    @RequestMapping({"/vets", "/vets/index", "vets/index.html"})
    public String listVets() {

        return "vets/index";
    }
}
