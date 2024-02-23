package elastickafkadebezium.product.request;

import elastickafkadebezium.product.response.CategoryResponse;

import java.util.List;

public record UpdateProduct_CategoryList_Request(
        String id,
        List<CategoryResponse> categoryList
) {
}
