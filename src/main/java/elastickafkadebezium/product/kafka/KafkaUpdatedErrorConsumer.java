package elastickafkadebezium.product.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaUpdatedErrorConsumer {

    private final KafkaPublisher kafkaPublisher;
    private static final String ORDER_CREATE_KAFKA_CONSUMER_ERROR = "product-updated.kafkaconsumer.error";
    private static final Map<String, Integer> retryCountsByTopicNames = new HashMap<>();
    private static final String RETRY_COUNT_KEY = "retryCount";
    private static final String ERROR_KEY = ".error";
    private static final String RETRY_KEY = ".retry";
    private static final String GROUP_ID = "KafkaUpdatedRetryJob-GroupId";
    private final ObjectMapper MAPPER = new ObjectMapper();


    @PostConstruct
    private void init() {
        retryCountsByTopicNames.put(ORDER_CREATE_KAFKA_CONSUMER_ERROR, 5);
    }



    @KafkaListener(topics = {
            ORDER_CREATE_KAFKA_CONSUMER_ERROR}, groupId = GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload Object object, ConsumerRecord consumerRecord) throws JsonProcessingException {
        String value = (String) consumerRecord.value();

        // Deger dizesini JSON dugumune donustur
        JsonNode payload = MAPPER.readTree(value);

        int retryCount = getRetryCount(payload);
        String errorTopic = consumerRecord.topic();
        if (isRetryable(errorTopic, retryCount)) {
            retryCount++;
            ((ObjectNode) payload).put(RETRY_COUNT_KEY, retryCount);
            String text = payload.toString();
            kafkaPublisher.publish(errorToRetryTopic(errorTopic), text);
        }
    }

    private static int getRetryCount(JsonNode jsonNode) {
        JsonNode retryCountNode = jsonNode.get("retryCount");
        return retryCountNode == null ? 0 : retryCountNode.asInt();
    }

    private boolean isRetryable(String topic, int currentRetryCount) {
        int retryCountLimit = retryCountsByTopicNames.getOrDefault(topic, 0);
        boolean limitless = retryCountLimit == 0;
        return limitless || currentRetryCount < retryCountLimit;
    }

    private String errorToRetryTopic(String errorTopic) {
        return errorTopic.replace(ERROR_KEY, RETRY_KEY);
    }
}

