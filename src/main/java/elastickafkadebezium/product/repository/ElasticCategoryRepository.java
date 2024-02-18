package elastickafkadebezium.product.repository;

import elastickafkadebezium.product.model.ElasticCategory;
import elastickafkadebezium.product.model.ElasticProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
public interface ElasticCategoryRepository extends ElasticsearchRepository<ElasticCategory,String> {
}
