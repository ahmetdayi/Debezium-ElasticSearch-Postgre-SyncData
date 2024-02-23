package elastickafkadebezium.product.converter;

import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryConverter {

    public List<CategoryResponse> convertResponse(List<Category> fromList){
        return fromList.stream().map(from -> new CategoryResponse(from.getId(), from.getName())).toList();
    }

    public List<Category> convert(List<CategoryResponse> fromList){
        return fromList.stream().map(from-> new Category(from.id(), from.name())).toList();
    }

}
