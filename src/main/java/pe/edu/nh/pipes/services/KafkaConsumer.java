package pe.edu.nh.pipes.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pe.edu.nh.pipes.model.Mensaje;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "modas", groupId = "group_id")
    public void listen(Mensaje message) {
        System.out.println("Mensaje Recibido: " + message);
    }
}
