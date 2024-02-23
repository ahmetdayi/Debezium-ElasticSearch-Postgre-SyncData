package elastickafkadebezium.product.model;

import lombok.Getter;

@Getter
public enum DebeziumPushTopic {
    CREATED("product-created"),UPDATED("product-updated"),DELETED("product-deleted");

    private final String topic;

    DebeziumPushTopic(String topic) {
        this.topic = topic;
    }

}

