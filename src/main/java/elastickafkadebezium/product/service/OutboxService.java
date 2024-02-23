package elastickafkadebezium.product.service;

import elastickafkadebezium.product.model.OutBox;
import elastickafkadebezium.product.repository.OutBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutBoxRepository outboxRepository;

    public void createOutbox(OutBox outbox)  {
        String key = UUID.randomUUID().toString();
        outbox.setAggregateId(key);
        outboxRepository.save(outbox);

    }

    public void deleteByProductId(String payload){
        List<OutBox> byPayload = outboxRepository.findByPayload(payload);
        outboxRepository.deleteAll(byPayload);
    }

    public List<OutBox> findAll() {
        return outboxRepository.findAll();
    }
}
