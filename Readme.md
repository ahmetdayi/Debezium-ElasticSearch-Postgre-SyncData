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

yukardaki outbox sinifi debeziumun takip edecegi siniftir. bunu debezium connectde belirttikten sonra
aggregationType uzerinden gonderecegi topice karar verirken takip etmesi icin aggregationId zorunludur.
payload postgrede JsonB formatinda tutulmalidir ancak springbootda bunu "@JdbcTypeCode(SqlTypes.JSON)" anatasyonuyla /
belirtebiliriz.

DbConnect for postgres:
```json
{
    "name": "outbox", // Kafka Connect bağlantı adı
    "config": {
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector", // Debezium PostgreSQL Connector'ın sınıf adı
        "tasks.max": "1", // Aynı anda çalıştırılacak görev sayısı
        "database.hostname": "db", // PostgreSQL veritabanı sunucusunun adı veya IP adresi
        "database.port": "5432", // PostgreSQL veritabanı sunucusunun port numarası
        "database.user": "postgres", // PostgreSQL veritabanına bağlanmak için kullanıcı adı
        "database.password": "example", // PostgreSQL veritabanına bağlanmak için kullanıcı parolası
        "database.dbname": "outbox", // PostgreSQL veritabanı adı
        "database.server.name": "postgres", // Debezium'un izlediği PostgreSQL sunucu adı
        "topic.prefix": "product", // Kafka topic adının ön eki
        "table.include.list": "public.out_box", // İzlenecek PostgreSQL tablosu adı (out_box olarak değiştirildi)
        "tombstones.on.delete": "false", // Silinen kayıtlar için tombstone oluşturulsun mu?
        "table.field.event.payload": "payload", // Değişiklik verisinin saklandığı alan adı
        "skipped.operations": "u,t,d", // Atlanacak işlemler: güncelleme, truncate, silme
        "key.converter": "org.apache.kafka.connect.json.JsonConverter", // Anahtar dönüştürücü sınıfı
        "key.converter.schemas.enable": "false", // Anahtarın şemalarını etkinleştir
        "value.converter.schemas.enable": "false", // Değerin şemalarını etkinleştir
        "transforms": "outbox", // Değişiklikleri yönlendirmek için dönüşümler
        "transforms.outbox.type": "io.debezium.transforms.outbox.EventRouter", // EventRouter dönüşüm sınıfı
        "transforms.outbox.table.expand.json.payload": "true", // JSON payload'ini genişlet
        "transforms.outbox.route.topic.replacement": "product_created", // Yönlendirilecek Kafka topic adı (product_created olarak değiştirildi)
        "value.converter": "org.apache.kafka.connect.json.JsonConverter", // Değer dönüştürücü sınıfı
        "route.topic.regex": "" // Kafka topic ismi değişiklikleri için regex ifadesi
    }
}

postman collectionu proje dosyalarinda bulunuyor