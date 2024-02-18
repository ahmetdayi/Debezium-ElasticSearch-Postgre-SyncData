package elastickafkadebezium.product.service;

import elastickafkadebezium.product.converter.ElasticCategoryConverter;
import elastickafkadebezium.product.model.ElasticProduct;
import elastickafkadebezium.product.repository.ElasticProductRepository;
import elastickafkadebezium.product.request.KafkaPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticProductService {
    private final ElasticProductRepository elasticProductRepository;
    private final ElasticCategoryConverter elasticCategoryConverter;

    public void create(KafkaPayload kafkaPayload) {
        ElasticProduct elasticProduct = new ElasticProduct(
                kafkaPayload.id(),
                kafkaPayload.name(),
                kafkaPayload.unitStock(),
                elasticCategoryConverter.convert(kafkaPayload.categoryList()));
        elasticProductRepository.save(elasticProduct);
    }
}
