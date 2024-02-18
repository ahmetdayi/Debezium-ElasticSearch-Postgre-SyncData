package elastickafkadebezium.product.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import elastickafkadebezium.product.model.OutBox;
import elastickafkadebezium.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class OutBoxConverter {
    private final ObjectMapper MAPPER = new ObjectMapper();
    public OutBox convert(Product product)  {
        try {
            return new OutBox("product-created","product-created",MAPPER.writeValueAsString(product));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
