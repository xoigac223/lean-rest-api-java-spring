package com.example.demo.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public UserSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public UserSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<ProductEntity> build() {
        if (params.size() == 0)
            return null;

        Specification<ProductEntity> result = new UserSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).or(new UserSpecification(params.get(i)));
        }

        return result;
    }
}
