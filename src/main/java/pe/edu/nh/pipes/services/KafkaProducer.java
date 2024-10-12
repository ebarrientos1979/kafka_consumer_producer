package pe.edu.nh.pipes.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.edu.nh.pipes.model.Mensaje;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, Mensaje> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Mensaje> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Mensaje message) {
        kafkaTemplate.send(topic, message);
    }
}
