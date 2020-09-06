package com.example.demo.product.mapping;

import com.example.demo.product.ProductEntity;
import com.example.demo.product.dto.ProductDTO;

public interface Imapping {
    public ProductEntity mappingDtoToEntity(ProductDTO productDTO);

}
