package vn.iotstar.service;


import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    public ProductService(ProductRepository productRepo) { this.productRepo = productRepo; }

    public List<Product> all(){ return productRepo.findAll(); }
    public List<Product> byPriceAsc(){ return productRepo.findAllByOrderByPriceAsc(); }
    public List<Product> byCategory(Long categoryId){ return productRepo.findByCategory_Id(categoryId); }
    public Optional<Product> one(Long id){ return productRepo.findById(id); }
    public Product save(Product p){ return productRepo.save(p); }
    public boolean delete(Long id){ if(!productRepo.existsById(id)) return false; productRepo.deleteById(id); return true; }
}