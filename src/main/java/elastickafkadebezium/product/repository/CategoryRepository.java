package elastickafkadebezium.product.repository;

import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findByIdIn(List<String> idList);
}
