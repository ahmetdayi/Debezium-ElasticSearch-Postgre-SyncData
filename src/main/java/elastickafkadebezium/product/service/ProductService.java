package elastickafkadebezium.product.service;

import elastickafkadebezium.product.converter.OutBoxConverter;
import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.model.Product;
import elastickafkadebezium.product.repository.ProductRepository;
import elastickafkadebezium.product.request.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final OutboxService outboxService;
    private final OutBoxConverter outBoxConverter;

    public void create(CreateProductRequest request){
        List<Category> categoryList = categoryService.findByIdIn(request.categoryIdList());
        Product product = new Product(request.name(),request.unitStock(),categoryList);
        Product savedProduct = productRepository.save(product);
        outboxService.createOutbox(outBoxConverter.convert(savedProduct));

    }
}
