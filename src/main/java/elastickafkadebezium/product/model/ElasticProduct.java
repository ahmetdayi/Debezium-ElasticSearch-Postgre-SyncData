package elastickafkadebezium.product.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticProduct {

    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private String unitStock;

    @Field(type = FieldType.Nested)
    private List<ElasticCategory> elasticCategoryList;

    public ElasticProduct(String id, String name, String unitStock) {
        this.id = id;
        this.name = name;
        this.unitStock = unitStock;
    }
}
