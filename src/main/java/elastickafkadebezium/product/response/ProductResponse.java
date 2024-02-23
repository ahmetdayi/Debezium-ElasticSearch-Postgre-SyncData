package elastickafkadebezium.product.response;

import java.util.List;

public record ProductResponse(
        String id,
        String name,
        String unitStock,
        List<CategoryResponse> categoryList
) {
}
