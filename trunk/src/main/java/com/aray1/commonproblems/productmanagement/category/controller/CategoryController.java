package com.aray1.commonproblems.productmanagement.category.controller;

import static com.aray1.commonproblems.productmanagement.ResourceConstants.BASE_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aray1.commonproblems.productmanagement.category.model.ProductCategoryRequest;
import com.aray1.commonproblems.productmanagement.category.model.ProductCategoryResponse;
import com.aray1.commonproblems.productmanagement.category.service.ProductCategoryService;

@RestController
@RequestMapping(BASE_PATH + "/categories")
public class CategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public CategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCategoryResponse> createCategory(
            @RequestBody ProductCategoryRequest productCategoryRequest) {

        ProductCategoryResponse productCategoryResponse = productCategoryService.createCategory(productCategoryRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productCategoryResponse.getId()).toUri();

        return ResponseEntity.created(location).body(productCategoryResponse);
    }

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ProductCategoryResponse getCategory(String id) {
        return productCategoryService.getCategory(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE, value = "/list")
    public List<ProductCategoryResponse> getAllCategories() {
        return productCategoryService.getAllCategories();
    }

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE, value = "/hierarchy")
    public List<ProductCategoryResponse> getCategoryHierarchy(String id) {
        return productCategoryService.getCategoryHierarchy(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteProductCategory(String id) {
        productCategoryService.deleteProductCategory(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, value = "/{id}")
    public void updateProductCategoryById(@PathVariable("id") String id,
            @RequestBody ProductCategoryRequest productCategoryRequest) {
        productCategoryService.updateProductCategory(id, productCategoryRequest);
    }

}
