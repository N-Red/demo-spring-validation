package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.form.ProductForm;
import com.example.demo.service.ProductService;
import com.example.demo.form.ProductUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(path = {"", "/", "/index"}, method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public ModelAndView findProductList() {
        return new ModelAndView("product/list-product", "products", productService.findAllProduct());
    }

    @RequestMapping(path = "/product/{productId}", method = RequestMethod.GET)
    public ModelAndView findProduct(@PathVariable(name = "productId") Integer productId) {
        return new ModelAndView("product/view-product", "product", productService.findProductById(productId));
    }

    /*CREATE*/
    @RequestMapping(path = "/createProduct", method = RequestMethod.GET)
    public ModelAndView getProductForm() {
        return new ModelAndView("product/create-product", "productForm", new ProductForm());
    }

    @RequestMapping(path = "/createProduct", method = RequestMethod.POST)
    public ModelAndView createProduct(@Valid @ModelAttribute("productForm") ProductForm productForm,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("product/create-product");
        }
        Product product = productService.createProduct(productForm);
        return new ModelAndView("redirect:/product/" + product.getProductId());
    }

    /*UPDATE*/
    @RequestMapping(path = "/product/update/{productId}", method = RequestMethod.GET)
    public ModelAndView findEditProduct(@PathVariable(name = "productId") Integer productId,
                                        ModelAndView modelAndView) {
        modelAndView.addObject("product", productService.findProductById(productId));
        modelAndView.addObject("productUpdateForm", new ProductUpdateForm());
        modelAndView.setViewName("product/update-product");
        return modelAndView;
    }

    @RequestMapping(path = "/product/update/{productId}", method = RequestMethod.POST)
    public ModelAndView updateProduct(@PathVariable(name = "productId") Integer productId,
                                      @Valid @ModelAttribute("productUpdateForm") ProductUpdateForm productUpdateForm,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("product/update-product", "product", productService.findProductById(productId));
        }
        productService.updateProduct(productId, productUpdateForm);
        return new ModelAndView("redirect:/product/" + productId);
    }

    /*DELETE*/
    @RequestMapping(path = "/product/delete/{productId}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable(name = "productId") Integer productId) {
        productService.deleteProductById(productId);
        return new ModelAndView("redirect:/products");
    }
}
