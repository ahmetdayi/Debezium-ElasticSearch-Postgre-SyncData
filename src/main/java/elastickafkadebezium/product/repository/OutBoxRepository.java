package elastickafkadebezium.product.repository;

import elastickafkadebezium.product.model.OutBox;
import elastickafkadebezium.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutBoxRepository extends JpaRepository<OutBox,String> {
    List<OutBox> findByPayload(String payload);
}
