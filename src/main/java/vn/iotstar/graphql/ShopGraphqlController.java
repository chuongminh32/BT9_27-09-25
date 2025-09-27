package vn.iotstar.graphql;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.repository.UserRepository;


import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@Transactional
public class ShopGraphqlController {


private final ProductRepository productRepo;
private final CategoryRepository categoryRepo;
private final UserRepository userRepo;


public ShopGraphqlController(ProductRepository productRepo,
CategoryRepository categoryRepo,
UserRepository userRepo) {
this.productRepo = productRepo;
this.categoryRepo = categoryRepo;
this.userRepo = userRepo;
}


// ===== Query =====
@QueryMapping
public List<Product> productsByPriceAsc() {
return productRepo.findAllByOrderByPriceAsc();
}


@QueryMapping
public List<Product> productsByCategory(@Argument Long categoryId) {
return productRepo.findByCategory_Id(categoryId);
}


@QueryMapping
public List<User> allUsers() { return userRepo.findAll(); }


@QueryMapping
public List<Category> allCategories() { return categoryRepo.findAll(); }


}