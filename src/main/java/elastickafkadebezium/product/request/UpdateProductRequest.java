package elastickafkadebezium.product.request;

public record UpdateProductRequest(
        String id,
        String name,
        String unitStock
        ) {
}
