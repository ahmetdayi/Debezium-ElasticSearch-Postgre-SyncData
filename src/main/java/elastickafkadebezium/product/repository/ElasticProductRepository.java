package elastickafkadebezium.product.repository;

import elastickafkadebezium.product.model.ElasticProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.List;

@EnableElasticsearchRepositories
public interface ElasticProductRepository extends ElasticsearchRepository<ElasticProduct,String> {
    List<ElasticProduct> findByElasticCategoryListNameLike(String categoryName);
    List<ElasticProduct> findByNameIgnoreCaseLikeOrUnitStockLikeOrUnitStockContainingOrElasticCategoryListNameLikeOrElasticCategoryListNameContainingOrNameContaining(String name, String unitStock, String unitStock2, String elasticCategoryList_name, String elasticCategoryList_name2 , String name2);
}
