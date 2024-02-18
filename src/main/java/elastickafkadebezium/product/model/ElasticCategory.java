package elastickafkadebezium.product.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticCategory {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;
}
