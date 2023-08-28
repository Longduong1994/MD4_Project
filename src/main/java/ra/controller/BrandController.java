package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.service.impl.BrandService;

@Controller
@RequestMapping("/")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("/brand")
    public ModelAndView adminBrand(Model model){
        model.addAttribute("brands", brandService.findAll());
        return new ModelAndView("admin/brand");
    }
}
