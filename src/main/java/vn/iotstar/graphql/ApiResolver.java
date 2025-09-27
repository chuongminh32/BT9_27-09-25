package vn.iotstar.graphql;
import vn.iotstar.entity.*;
import vn.iotstar.repository.*;
import vn.iotstar.service.*;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class ApiResolver {
    private final UserService userService; private final CategoryService categoryService; private final ProductService productService;
    private final UserRepository userRepo; private final CategoryRepository categoryRepo; private final ProductRepository productRepo;

    public ApiResolver(UserService u, CategoryService c, ProductService p,
                       UserRepository ur, CategoryRepository cr, ProductRepository pr){
        this.userService=u; this.categoryService=c; this.productService=p;
        this.userRepo=ur; this.categoryRepo=cr; this.productRepo=pr;
    }

    // ===== Queries =====
    @QueryMapping public List<User> users(){ return userService.all(); }
    @QueryMapping public List<Category> categories(){ return categoryService.all(); }
    @QueryMapping public List<Product> products(){ return productService.all(); }
    @QueryMapping public List<Product> productsByPriceAsc(){ return productService.byPriceAsc(); }
    @QueryMapping public List<Product> productsByCategory(@Argument Long categoryId){ return productService.byCategory(categoryId); }

    @QueryMapping public Optional<User> user(@Argument Long id){ return userService.one(id); }
    @QueryMapping public Optional<Category> category(@Argument Long id){ return categoryService.one(id); }
    @QueryMapping public Optional<Product> product(@Argument Long id){ return productService.one(id); }

    // ===== Field mapping (nếu cần lazy loading) =====
    @SchemaMapping(typeName = "Product", field = "user")
    public User productUser(Product p){ return p.getUser(); }

    @SchemaMapping(typeName = "Product", field = "category")
    public Category productCategory(Product p){ return p.getCategory(); }

    @SchemaMapping(typeName = "Category", field = "products")
    public List<Product> categoryProducts(Category c){ return productRepo.findByCategoryId(c.getId()); }

    // ===== Inputs dạng record =====
    public record UserIn(String fullname, String email, String password, String phone, List<Long> categoryIds){}
    public record CategoryIn(String name, String images){}
    public record ProductIn(String title, Integer quantity, String desc, Double price, Long userId, Long categoryId){}

    // ===== Mutations =====
    @MutationMapping
    public User createUser(@Argument("input") UserIn in){
        User u = User.builder().fullname(in.fullname()).email(in.email())
                .password(in.password()).phone(in.phone()).build();
        if (in.categoryIds()!=null && !in.categoryIds().isEmpty()) {
            u.setCategories(new HashSet<>(categoryRepo.findAllById(in.categoryIds())));
        }
        return userService.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument("input") UserIn in){
        User u = userService.one(id).orElseThrow();
        u.setFullname(in.fullname()); u.setEmail(in.email()); u.setPassword(in.password()); u.setPhone(in.phone());
        if (in.categoryIds()!=null) {
            u.setCategories(new HashSet<>(categoryRepo.findAllById(in.categoryIds())));
        }
        return userService.save(u);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id){ return userService.delete(id); }

    @MutationMapping
    public Category createCategory(@Argument("input") CategoryIn in){
        Category c = Category.builder().name(in.name()).images(in.images()).build();
        return categoryService.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument("input") CategoryIn in){
        Category c = categoryService.one(id).orElseThrow();
        c.setName(in.name()); c.setImages(in.images());
        return categoryService.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id){ return categoryService.delete(id); }

    @MutationMapping
    public Product createProduct(@Argument("input") ProductIn in){
        Product p = new Product();
        p.setTitle(in.title()); p.setQuantity(in.quantity()); p.setDesc(in.desc());
        p.setPrice(java.math.BigDecimal.valueOf(in.price()));
        if (in.userId()!=null) p.setUser(userRepo.findById(in.userId()).orElse(null));
        if (in.categoryId()!=null) p.setCategory(categoryRepo.findById(in.categoryId()).orElse(null));
        return productService.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument("input") ProductIn in){
        Product p = productService.one(id).orElseThrow();
        p.setTitle(in.title()); p.setQuantity(in.quantity()); p.setDesc(in.desc());
        p.setPrice(java.math.BigDecimal.valueOf(in.price()));
        if (in.userId()!=null) p.setUser(userRepo.findById(in.userId()).orElse(null));
        if (in.categoryId()!=null) p.setCategory(categoryRepo.findById(in.categoryId()).orElse(null));
        return productService.save(p);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id){ return productService.delete(id); }
}