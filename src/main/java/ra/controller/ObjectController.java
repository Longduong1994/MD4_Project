package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ra.service.impl.CatalogService;
import ra.service.impl.ObjectService;
import ra.service.impl.ProductService;

@Controller
@RequestMapping("/")
public class ObjectController {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService catalogService;
    @GetMapping("/object")
    public ModelAndView adminBrand(Model model){
        model.addAttribute("objects", objectService.findAll());
        return new ModelAndView("admin/object");
    }


}
