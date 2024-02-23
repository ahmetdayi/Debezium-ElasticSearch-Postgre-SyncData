package elastickafkadebezium.product.service;

import elastickafkadebezium.product.converter.CategoryConverter;
import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.repository.CategoryRepository;
import elastickafkadebezium.product.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;


    public List<CategoryResponse> findAll(){
        return categoryConverter.convertResponse(categoryRepository.findAll());
    }

    protected List<Category> findByIdIn(List<String> idList){
        return categoryRepository.findByIdIn(idList);
    }

}
