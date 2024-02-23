package elastickafkadebezium.product.converter;

import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.model.ElasticCategory;
import elastickafkadebezium.product.response.ElasticCategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticCategoryConverter {

    public List<ElasticCategory> convert(List<Category> fromList){
        if (fromList ==null){
            return null;
        }
        return fromList.stream().map(category -> new ElasticCategory(category.getId(), category.getName())).toList();
    }
    public List<ElasticCategoryResponse> convertResponse(List<ElasticCategory> fromList){
        if (fromList ==null){
            return null;
        }
        return fromList.stream().map(from -> new ElasticCategoryResponse(from.getId(), from.getName())).toList();
    }


}
