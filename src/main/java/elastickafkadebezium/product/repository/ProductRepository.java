package elastickafkadebezium.product.repository;

import elastickafkadebezium.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
