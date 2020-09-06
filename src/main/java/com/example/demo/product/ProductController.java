package com.example.demo.product;

import com.example.demo.product.dto.ProductDTO;
import com.sipios.springsearch.anotation.SearchSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;


@Controller // định nghĩa api 4 phần: path, method, body, header
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<ProductEntity>> findAll(){

        return ResponseEntity.ok(productServices.findAll());
    }
    @GetMapping("/api/v1/search")
    public ResponseEntity<List<ProductEntity>> search(@SearchSpec Specification<ProductEntity> specs){
        return ResponseEntity.ok(productServices.search(specs));
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductEntity> newEntity(@RequestBody ProductDTO newProductDto){
        return ResponseEntity.ok(productServices.createProduct(newProductDto));
    }
    @DeleteMapping("/api/v1/products")
    public ResponseEntity<Boolean> deleteEntity(@RequestParam("id") long id){
       return ResponseEntity.ok(productServices.deleteById(id));
    }
    @PutMapping("/api/v1/products/{id}")
    public ResponseEntity<Boolean> updateEntity(@PathVariable("id") long id, @RequestBody ProductEntity productEntity){
        return ResponseEntity.ok(productServices.update(id, productEntity));
    }
    @GetMapping("/api/v1/products-query")
    public ResponseEntity<List<ProductEntity>> findAllQuery(){
        return ResponseEntity.ok(productServices.findAllQuery());
    }
    @PostMapping("/api/v1/products-query")
    public ResponseEntity<ProductEntity> newEntityQuery(@RequestBody ProductEntity newProductEntity){
        return ResponseEntity.ok(productServices.createProductQuery(newProductEntity));
    }
    @DeleteMapping("/api/v1/products-query")
    public ResponseEntity<Boolean> deleteEntityQuery(@RequestParam("id") long id){
        return ResponseEntity.ok(productServices.deleteByIdQuery(id));
    }
    @PutMapping("/api/v1/products-query/{id}")
    public ResponseEntity<Boolean> updateEntityQuery(@PathVariable("id") long id, @RequestBody ProductEntity productEntity){
        return ResponseEntity.ok(productServices.updateQuery(id, productEntity));
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<ProductEntity>> search(@RequestParam(value = "search") String search) {
        return ResponseEntity.ok(productServices.search(search));
    }
}
