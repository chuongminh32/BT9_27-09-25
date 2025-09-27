package vn.iotstar.repository;

import vn.iotstar.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Lấy product theo giá tăng dần
    List<Product> findAllByOrderByPriceAsc();

    // Lấy product theo category
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findByCategoryId(Long categoryId);
}