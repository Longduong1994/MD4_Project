package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.service.impl.CatalogService;

@Controller
@RequestMapping("/")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;
    @GetMapping("/category")
    public ModelAndView adminCatalog(Model model){
        model.addAttribute("catalogs", catalogService.findAll());
        return new ModelAndView("admin/category");
    }

}
