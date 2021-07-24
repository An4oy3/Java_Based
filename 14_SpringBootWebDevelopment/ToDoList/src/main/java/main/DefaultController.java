package main;

import main.model.Casse;
import main.model.CasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class DefaultController {
    @Autowired
    private CasseRepository casseRepository;

    @Value("${someParameter}")
    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Casse> casseIterable = casseRepository.findAll();
        ArrayList<Casse> casseArrayList = new ArrayList<>();
        for (Casse casse : casseIterable) {
            casseArrayList.add(casse);
        }
        model.addAttribute("Casse", casseArrayList);
        model.addAttribute("casseCount", casseArrayList.size());
        model.addAttribute("someParameter", someParameter);
        return "index";
    }
}
