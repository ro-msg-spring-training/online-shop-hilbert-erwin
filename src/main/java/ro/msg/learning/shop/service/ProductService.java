package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product create(ProductDto productDto) {
        ProductCategory productCategory = productCategoryRepository.findByName(productDto.getProductCategoryName());
        Supplier supplier = supplierRepository.findByName(productDto.getSupplierName());

        if(productCategory == null) {
            productCategory = ProductCategory.builder().name(productDto.getProductCategoryName())
                    .description(productDto.getProductCategoryDescription()).build();
            productCategoryRepository.save(productCategory);
        }

        if(supplier == null) {
            supplier = Supplier.builder().name(productDto.getSupplierName()).build();
            supplierRepository.save(supplier);
        }

        Product product = Product.builder().name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .image_url(productDto.getImageUrl())
                .product_category(productCategory)
                .supplier(supplier).build();
        productRepository.save(product);

        return product;
    }

    public Product update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return create(productDto);
        } else {
            ProductCategory productCategory = productCategoryRepository.findByName(productDto.getProductCategoryName());
            Supplier supplier = supplierRepository.findByName(productDto.getSupplierName());

            if(productCategory == null) {
                productCategory = ProductCategory.builder().name(productDto.getProductCategoryName())
                        .description(productDto.getProductCategoryDescription()).build();
                productCategoryRepository.save(productCategory);
            } else {
                productCategory.setName(productDto.getProductCategoryName());
                productCategory.setDescription(productDto.getDescription());
            }

            if(supplier == null) {
                supplier = Supplier.builder().name(productDto.getSupplierName()).build();
                supplierRepository.save(supplier);
            } else {
                supplier.setName(productDto.getSupplierName());
            }

            product.setName(product.getName());
            product.setDescription(product.getDescription());
            product.setPrice(product.getPrice());
            product.setWeight(product.getWeight());
            product.setImage_url(product.getImage_url());
            product.setProduct_category(productCategory);
            product.setSupplier(supplier);

            productRepository.save(product);
            productCategoryRepository.save(productCategory);
            supplierRepository.save(supplier);

            return product;
        }
    }

}
