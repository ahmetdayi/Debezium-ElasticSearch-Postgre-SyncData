package elastickafkadebezium.product.response;

import java.util.List;

public record ElasticProductResponse(String id, String name, String unitStock,
                                     List<ElasticCategoryResponse> elasticCategoryList) {
}
