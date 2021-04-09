package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        List<Product> products = productService.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            productDtos.add(new ProductDto(product, product.getProduct_category(), product.getSupplier()));
        }

        return productDtos;
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if(product == null) {
            return null;
        } else {
            return new ProductDto(product, product.getProduct_category(),product.getSupplier());
        }
    }

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto) {
        productService.create(productDto);
        return new ResponseEntity<>("Product created", HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductDto product) {
        productService.update(id, product);
        return new ResponseEntity<>("Product updated", HttpStatus.OK);
    }
}
