package com.example.demo.product;

import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.mapping.Imapping;
import com.example.demo.product.mapping.MappingProductImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service // Xử lý logic
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
  @Autowired
    private Imapping imapping;
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findAllQuery(){
        return productRepository.findAllQuery();
    }

    public ProductEntity createProduct(ProductDTO productDTO){
        return productRepository.save(imapping.mappingDtoToEntity(productDTO));
    }
    public ProductEntity createProductQuery(ProductEntity productEntity){
        productRepository.insertUse(productEntity.getName(), productEntity.getDescription());
        return productEntity;
    }
    public boolean deleteById(long id){
        try {
            Optional<ProductEntity> productEntityData = productRepository.findById(id);
            if (productEntityData.isPresent()){
                productRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
    public boolean deleteByIdQuery(long id){
        try {
            Optional<ProductEntity> productEntityData = productRepository.findById(id);
            if (productEntityData.isPresent()){
                productRepository.deleteByIdQuery(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
    public boolean update(long id, ProductEntity productEntity){
        try {
            Optional<ProductEntity> productEntityData = productRepository.findById(id);
            if (productEntityData.isPresent()){
                ProductEntity _productEntity = productEntityData.get();
                _productEntity.setName(productEntity.getName());
                _productEntity.setDescription(productEntity.getDescription());
                productRepository.save(_productEntity);
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
    public boolean updateQuery(long id, ProductEntity productEntity){
        try {
            Optional<ProductEntity> productEntityData = productRepository.findById(id);
            if (productEntityData.isPresent()){
                productRepository.updateQuery(productEntity.getName(), productEntity.getDescription(), id);
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
    public List<ProductEntity> search(Specification<ProductEntity> specs){
        return productRepository.findAll(specs);
    }
    public List<ProductEntity> search(String search){
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<ProductEntity> spec = builder.build();
        return productRepository.findAll(spec);
    }

}
