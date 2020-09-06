package com.example.demo.product.mapping;

import com.example.demo.product.ProductEntity;
import com.example.demo.product.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class MappingProductImp implements Imapping {
    @Override
    public ProductEntity mappingDtoToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setDescription("string default");
        return productEntity;
    }
}
