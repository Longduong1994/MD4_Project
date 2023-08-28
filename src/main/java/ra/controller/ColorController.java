package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.service.impl.ColorService;

@Controller
@RequestMapping("/")
public class ColorController {
    @Autowired
    private ColorService colorService;
    @GetMapping("/color")
    public ModelAndView adminBrand(Model model){
        model.addAttribute("colors", colorService.findAll());
        return new ModelAndView("admin/color");
    }
}
