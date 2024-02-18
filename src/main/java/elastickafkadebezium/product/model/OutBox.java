package elastickafkadebezium.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "outbox")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutBox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "varchar(255)", name = "aggregatetype")
    private String aggregateType;

    @Column(columnDefinition = "varchar(255)", name = "aggregateid")
    private String aggregateId;

    @Column(columnDefinition = "varchar(255)")
    private String type;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String payload;
    public OutBox(String aggregateType, String aggregateId, String type, String payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.type = type;
        this.payload = payload;
    }

    public OutBox(String aggregateType, String type, String payload) {
        this.aggregateType = aggregateType;
        this.type = type;
        this.payload = payload;
    }

    public OutBox(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }
}
