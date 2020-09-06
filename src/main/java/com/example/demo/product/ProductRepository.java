package com.example.demo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Repository //(dao) truy vấn dữ liệu
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity>{
    @Query(value = "SELECT p FROM product_demo p")
    List<ProductEntity> findAllQuery();

    @Modifying
    @Transactional
    @Query(
            value =
                    "insert into product_demo ( name, description) values ( :name, :description)",
            nativeQuery = true)
    void insertUse(@Param("name") String name,
                   @Param("description") String description);

    @Modifying
    @Transactional
    @Query(
            value =
                    "delete from product_demo where id =:id",
            nativeQuery = true)
    void deleteByIdQuery(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_demo SET name = :name, description = :description WHERE id = :id",
            nativeQuery = true)
    void updateQuery(@Param("name") String name, @Param("description") String description, @Param("id") long id);
}
