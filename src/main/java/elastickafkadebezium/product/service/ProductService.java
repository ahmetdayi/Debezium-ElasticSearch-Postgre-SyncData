package elastickafkadebezium.product.service;

import elastickafkadebezium.product.converter.CategoryConverter;
import elastickafkadebezium.product.converter.OutBoxConverter;
import elastickafkadebezium.product.converter.ProductConverter;
import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.model.DebeziumPushTopic;
import elastickafkadebezium.product.model.Product;
import elastickafkadebezium.product.repository.ProductRepository;
import elastickafkadebezium.product.request.CreateProductRequest;
import elastickafkadebezium.product.request.UpdateProductRequest;
import elastickafkadebezium.product.request.UpdateProduct_CategoryList_Request;
import elastickafkadebezium.product.response.ProductResponse;
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
    private final ProductConverter productConverter;
    private final CategoryConverter categoryConverter;

    public void create(CreateProductRequest request) {
        List<Category> categoryList = categoryService.findByIdIn(request.categoryIdList());
        Product product = new Product(request.name(), request.unitStock(), categoryList);
        Product savedProduct = productRepository.save(product);
        outboxService.createOutbox(outBoxConverter.convert(savedProduct,DebeziumPushTopic.CREATED));

    }

    public void update(UpdateProductRequest request) {
        Product prod = findById(request.id());
        prod.setName(request.name().trim().isEmpty() ? prod.getName() : request.name());
        prod.setUnitStock(request.unitStock().trim().isEmpty() ? prod.getUnitStock() : request.unitStock());
        Product savedProduct = productRepository.save(prod);
        outboxService.createOutbox(outBoxConverter.convert(savedProduct,DebeziumPushTopic.UPDATED));
    }

    public void updateCategoryList(UpdateProduct_CategoryList_Request request) {
        Product prod = findById(request.id());

        if (request.categoryList() != null ){
            prod.getCategoryList().clear();
            prod.getCategoryList().addAll(categoryConverter.convert(request.categoryList()));
        }
        Product savedProduct = productRepository.save(prod);
        outboxService.createOutbox(outBoxConverter.convert(savedProduct,DebeziumPushTopic.UPDATED));
    }

    public void delete(String id) {
        Product prod = findById(id);
        outboxService.createOutbox(outBoxConverter.convert(prod,DebeziumPushTopic.DELETED));
        productRepository.delete(prod);

    }

    public List<ProductResponse> findAll() {
        return productConverter.convert(productRepository.findAll());
    }

    public ProductResponse getById(String id) {
        return productConverter.convert(findById(id));
    }

    private Product findById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product is not exist."));
    }
}
