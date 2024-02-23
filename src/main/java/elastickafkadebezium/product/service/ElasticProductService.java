package elastickafkadebezium.product.service;

import elastickafkadebezium.product.converter.ElasticCategoryConverter;
import elastickafkadebezium.product.converter.ElasticProductConverter;
import elastickafkadebezium.product.model.ElasticProduct;
import elastickafkadebezium.product.repository.ElasticProductRepository;
import elastickafkadebezium.product.request.KafkaPayload;
import elastickafkadebezium.product.response.ElasticProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ElasticProductService {
    private final ElasticProductRepository elasticProductRepository;
    private final ElasticCategoryConverter elasticCategoryConverter;
    private final ElasticProductConverter elasticProductConverter;

    public void create(KafkaPayload kafkaPayload) {
        //ilk basta o id ye sahip olan var mi diye bak
        ElasticProduct elasticProduct = new ElasticProduct(
                kafkaPayload.id(),
                kafkaPayload.name(),
                kafkaPayload.unitStock(),
                elasticCategoryConverter.convert(kafkaPayload.categoryList()));
        elasticProductRepository.save(elasticProduct);
    }

    //TODO sadece category listesini gunellemek istediginde hata olusabilir
    public void update(KafkaPayload kafkaPayload){
        ElasticProduct elasticProduct = findById(kafkaPayload.id());
        elasticProduct.setName(kafkaPayload.name().trim().isEmpty() ? elasticProduct.getName() : kafkaPayload.name());
        elasticProduct.setUnitStock(
                kafkaPayload.unitStock().trim().isEmpty() ? elasticProduct.getUnitStock() : kafkaPayload.unitStock());
        elasticProduct.setElasticCategoryList(
                kafkaPayload.categoryList().isEmpty() ? elasticProduct.getElasticCategoryList()
                        : elasticCategoryConverter.convert(kafkaPayload.categoryList()));
        elasticProductRepository.save(elasticProduct);

    }

    public void delete(KafkaPayload kafkaPayload){
         elasticProductRepository.delete(findById(kafkaPayload.id()));
    }

    public List<ElasticProductResponse> findAll(){
        return elasticProductConverter.convert(StreamSupport.stream(elasticProductRepository.findAll().spliterator(), false).toList());
    }
    private ElasticProduct findById(String id){
        return elasticProductRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Product is not exist in ElasticDB."));
    }
}
