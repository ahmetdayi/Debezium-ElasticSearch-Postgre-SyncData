package elastickafkadebezium.product.converter;

import elastickafkadebezium.product.model.ElasticProduct;
import elastickafkadebezium.product.response.ElasticProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ElasticProductConverter {
    private final ElasticCategoryConverter elasticCategoryConverter;

    public List<ElasticProductResponse> convert(List<ElasticProduct> fromList){
        if (fromList ==null){
            return null;
        }
        return fromList
                .stream()
                .map(from -> new ElasticProductResponse(
                        from.getId(),
                        from.getName(),
                        from.getUnitStock(),
                        elasticCategoryConverter.convertResponse(from.getElasticCategoryList())))
                .toList();
    }
}
