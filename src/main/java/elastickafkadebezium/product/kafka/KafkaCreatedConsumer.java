package elastickafkadebezium.product.kafka;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import elastickafkadebezium.product.request.KafkaPayload;
import elastickafkadebezium.product.service.ElasticProductService;
import elastickafkadebezium.product.service.OutboxService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaCreatedConsumer {
    private static final String TOPIC_NAME = "product-created";
    private static final String RETRY_TOPIC_NAME = "product-created.kafkaconsumer.retry";
    private static final String ERROR_TOPIC_NAME = "product-created.kafkaconsumer.error";
    private static final String GROUP_ID = "CreatedGroupId";


    private final ElasticProductService elasticProductService;

    private final OutboxService outboxService;
    private final KafkaPublisher kafkaPublisher;
    private final ObjectMapper MAPPER = new ObjectMapper();

    @KafkaListener(topics = {TOPIC_NAME}, groupId = GROUP_ID)
    public void listener(@Payload Object event, ConsumerRecord c) throws Exception {

        // Kafka kaydinin degerini bir dize olarak al
        String value = (String) c.value();

        // Deger dizesini JSON dugumune donustur
        JsonNode payload = MAPPER.readTree(value);
        String text = payload.toString();

        try {

            // JSON'dan KafkaPayload nesnesine donusturme islemi
            KafkaPayload kafkaPayload = MAPPER.readValue(text, KafkaPayload.class);

            // elastice verileri kaydetme
            elasticProductService.create(kafkaPayload);
            //elastice kaydedilen veriler bir hata olmazsa siliniyor
            outboxService.deleteByProductId(MAPPER.writeValueAsString(kafkaPayload));
        }catch (Exception e){
            kafkaPublisher.publish(RETRY_TOPIC_NAME, text);
        }


    }
    @KafkaListener(topics = RETRY_TOPIC_NAME, groupId = GROUP_ID)
    public void listener2(@Payload Object event, ConsumerRecord c) throws Exception {
        // Kafka kaydinin degerini bir dize olarak al
        String value = (String) c.value();

        // Deger dizesini JSON dugumune donustur
        JsonNode payload = MAPPER.readTree(value);
        String text = payload.toString();

        try {
            // JSON'dan KafkaPayload nesnesine donusturme islemi
            KafkaPayload kafkaPayload = MAPPER.readValue(text, KafkaPayload.class);
            elasticProductService.create(kafkaPayload);

        }catch (Exception e){
            kafkaPublisher.publish(ERROR_TOPIC_NAME, text);
        }
    }
}
