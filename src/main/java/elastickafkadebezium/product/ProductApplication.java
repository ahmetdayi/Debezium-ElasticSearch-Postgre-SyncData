package elastickafkadebezium.product;

import elastickafkadebezium.product.model.Category;
import elastickafkadebezium.product.repository.CategoryRepository;
import elastickafkadebezium.product.repository.ElasticProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

	private final ElasticProductRepository elasticProductRepository;
	private final CategoryRepository categoryRepository;

    public ProductApplication(ElasticProductRepository elasticProductRepository, CategoryRepository categoryRepository) {
        this.elasticProductRepository = elasticProductRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ahmet ");
		elasticProductRepository.findAll().forEach( System.out::println);

//		categoryRepository.saveAll(List.of(new Category("1","a"),new Category("2","b")));
		categoryRepository.findAll().forEach(System.out::println);


	}
}
