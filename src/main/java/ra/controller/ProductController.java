package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.dto.request.FormProductDto;
import ra.model.Product;
import ra.service.impl.ProductService;

import java.io.File;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Value("${upload-path}")
    private  String pathUpload;
    @GetMapping("product")
    public ModelAndView adminProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return new ModelAndView("admin/product");
    }


    @GetMapping("create")
    public ModelAndView adminCreateProduct() {
        return new ModelAndView("admin/create","product",new Product());
    }

    @PostMapping("/add-product")
    public String createProduct( @ModelAttribute("product") FormProductDto formProductDto){
        File file = new File(pathUpload);
        if(!file.exists()) {
            file.mkdir();
        }
        String  urlImg = formProductDto.getImage_url().getOriginalFilename();
        try{
            FileCopyUtils.copy(formProductDto.getImage_url().getBytes(), new File(pathUpload + urlImg));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        Product p = new Product();
        p.setId(formProductDto.getId());
        p.setProduct_name(formProductDto.getProduct_name());
        p.setImage_url(urlImg);
        p.setDescription(formProductDto.getDescription());
        p.setStock(formProductDto.getStock());
        p.setImport_price(formProductDto.getImport_price());
        p.setExport_price(formProductDto.getExport_price());
        p.setCatalog(formProductDto.getCatalog());
        p.setStatus(formProductDto.isStatus());
        productService.save(p);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/product";
    }
    @GetMapping("edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id){
        return new ModelAndView("admin/edit","product",productService.findById(id));
    }
    @PostMapping("/edit_product")
    public String editProduct( @ModelAttribute("product") FormProductDto formProductDto){
        File file = new File(pathUpload);
        if(!file.exists()) {
            file.mkdir();
        }
        String  urlImg = formProductDto.getImage_url().getOriginalFilename();
        try{
            FileCopyUtils.copy(formProductDto.getImage_url().getBytes(), new File(pathUpload + urlImg));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        Product p = new Product();
        p.setId(formProductDto.getId());
        p.setProduct_name(formProductDto.getProduct_name());
        p.setImage_url(urlImg);
        p.setDescription(formProductDto.getDescription());
        p.setStock(formProductDto.getStock());
        p.setImport_price(formProductDto.getImport_price());
        p.setExport_price(formProductDto.getExport_price());
        p.setCatalog(formProductDto.getCatalog());
        p.setStatus(formProductDto.isStatus());
        productService.save(p);
        return "redirect:/product";
    }


}
