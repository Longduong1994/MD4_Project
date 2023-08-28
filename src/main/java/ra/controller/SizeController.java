package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.service.impl.SizeService;

@Controller
@RequestMapping("/")
public class SizeController {
    @Autowired
    private SizeService sizeService;
    @GetMapping("/size")
    public ModelAndView adminSize(Model model){
        model.addAttribute("sizes", sizeService.findAll());
        return new ModelAndView("admin/size");
    }
}
