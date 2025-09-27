package vn.iotstar.service;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;
    public CategoryService(CategoryRepository categoryRepo) { this.categoryRepo = categoryRepo; }

    public List<Category> all(){ return categoryRepo.findAll(); }
    public Optional<Category> one(Long id){ return categoryRepo.findById(id); }
    public Category save(Category c){ return categoryRepo.save(c); }
    public boolean delete(Long id){ if(!categoryRepo.existsById(id)) return false; categoryRepo.deleteById(id); return true; }
}