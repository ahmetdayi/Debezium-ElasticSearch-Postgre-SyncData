package elastickafkadebezium.product.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import elastickafkadebezium.product.model.DebeziumPushTopic;
import elastickafkadebezium.product.model.OutBox;
import elastickafkadebezium.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class OutBoxConverter {
    private final ObjectMapper MAPPER = new ObjectMapper();
    public OutBox convert(Product product, DebeziumPushTopic debeziumPushTopic)  {
        try {
            return new OutBox(debeziumPushTopic.getTopic(),debeziumPushTopic.getTopic(),MAPPER.writeValueAsString(product));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
