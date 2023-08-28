package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Catalog;
import ra.service.impl.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class CatalogController {
    private static final Gson GSON = new GsonBuilder().create();
    @Autowired
    private CatalogService catalogService;
    @GetMapping("/category")
    public ModelAndView adminCatalog(Model model){
        model.addAttribute("catalogs", catalogService.findAll());
        return new ModelAndView("admin/category");
    }

    @PostMapping("/create-catalog")
    public String createCategory(@ModelAttribute("catalog") Catalog catalog, @RequestParam("catalog_name") String catalog_name) {
        catalog.setCatalog_name(catalog_name);
        catalogService.save(catalog);
        return "redirect:/category";
    }

    @GetMapping("/edit-catalog{id}")
    public Catalog getCategoryInfo(HttpServletResponse response, @PathVariable("id") int id) {
        Catalog catalog = catalogService.findById(id);
        String data = GSON.toJson(catalog);
        response.setHeader("Content-Type","application/json");
        response.setStatus(200);
        PrintWriter out=null;
        try {
            out = response.getWriter();
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            out.close();
        }
        return catalog;
    }

    @PostMapping("edit-catalog")
    public String editCatalog(@ModelAttribute Catalog catalog){
        catalogService.save(catalog);
        return "redirect:/category";
    }


    @GetMapping("/deleteCatalog/{id}")
    public String deleteCatalog(@PathVariable("id") int id){
        catalogService.delete(id);
        return "redirect:/category";
    }

}
