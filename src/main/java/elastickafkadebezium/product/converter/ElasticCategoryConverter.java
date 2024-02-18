package elastickafkadebezium.product.converter;

import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.model.ElasticCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticCategoryConverter {

    public List<ElasticCategory> convert(List<Category> fromList){
        return fromList.stream().map(category -> new ElasticCategory(category.getId(), category.getName())).toList();
    }

}
