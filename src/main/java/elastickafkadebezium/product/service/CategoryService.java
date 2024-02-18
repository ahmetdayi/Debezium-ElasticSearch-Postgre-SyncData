package elastickafkadebezium.product.service;

import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.repository.CategoryRepository;
import elastickafkadebezium.product.request.KafkaPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<Category> findByIdIn(List<String> idList){
        return categoryRepository.findByIdIn(idList);
    }

}
