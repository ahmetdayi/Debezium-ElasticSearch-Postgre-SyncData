package elastickafkadebezium.product.request;

import elastickafkadebezium.product.model.Category;

import java.util.ArrayList;
import java.util.List;

public record KafkaPayload(
        String id,
        String name,
        String unitStock,
        List<Category> categoryList
) {
    public KafkaPayload {
        // Özel yapılandırıcı kullanarak categoryList'i null değil, boş bir liste ile başlat
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
    }
}
