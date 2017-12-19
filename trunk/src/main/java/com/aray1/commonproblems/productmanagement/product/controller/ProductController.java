package com.aray1.commonproblems.productmanagement.product.controller;

import static com.aray1.commonproblems.productmanagement.ResourceConstants.BASE_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aray1.commonproblems.productmanagement.product.model.ProductRequest;
import com.aray1.commonproblems.productmanagement.product.model.ProductResponse;
import com.aray1.commonproblems.productmanagement.product.service.ProductService;

@RestController
@RequestMapping(BASE_PATH + "/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse product = productService.createProduct(productRequest);

        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).body(product);
    }

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ProductResponse getProduct(String id) {
        return productService.getProductById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE, value = "/list")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteProduct(String id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, value = "/{id}")
    public void updateProductById(@PathVariable("id") String id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }
}
