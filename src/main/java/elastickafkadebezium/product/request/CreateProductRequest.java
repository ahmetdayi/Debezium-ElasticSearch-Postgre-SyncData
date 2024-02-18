package elastickafkadebezium.product.request;

import java.util.List;

public record CreateProductRequest(
        String name,
        String unitStock,
        List<String> categoryIdList
) {
}
