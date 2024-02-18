package elastickafkadebezium.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import elastickafkadebezium.product.kafka.KafkaPublisher;
import elastickafkadebezium.product.model.OutBox;
import elastickafkadebezium.product.repository.OutBoxRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutBoxRepository outboxRepository;
    private final KafkaPublisher kafkaPublisher;
    private final ObjectMapper MAPPER = new ObjectMapper();

    public void createOutbox(OutBox outbox)  {
        String key = UUID.randomUUID().toString();
        outbox.setAggregateId(key);
        outboxRepository.save(outbox);

    }

    public void deleteByProductId(String payload){
        outboxRepository.deleteAll(outboxRepository.findByPayload(payload));
    }

    public List<OutBox> findAll() {
        return outboxRepository.findAll();
    }
}
