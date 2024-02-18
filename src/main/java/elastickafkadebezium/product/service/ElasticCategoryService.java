package elastickafkadebezium.product.service;

import elastickafkadebezium.product.converter.ElasticCategoryConverter;
import elastickafkadebezium.product.repository.ElasticCategoryRepository;
import elastickafkadebezium.product.request.KafkaPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticCategoryService {
    private final ElasticCategoryRepository elasticCategoryRepository;

    private final ElasticCategoryConverter elasticCategoryConverter;

    public void createAll(KafkaPayload kafkaPayload){
        elasticCategoryRepository.saveAll(elasticCategoryConverter.convert(kafkaPayload.categoryList()));
    }
}
