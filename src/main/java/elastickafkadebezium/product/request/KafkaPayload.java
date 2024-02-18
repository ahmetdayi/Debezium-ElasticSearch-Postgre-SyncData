package elastickafkadebezium.product.request;

import elastickafkadebezium.product.model.Category;

import java.util.List;

public record KafkaPayload(
        String id,
        String name,
        String unitStock,
        List<Category> categoryList
) {
}
