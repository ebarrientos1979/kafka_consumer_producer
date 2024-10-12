package pe.edu.nh.pipes.controller;

import pe.edu.nh.pipes.model.Mensaje;
import pe.edu.nh.pipes.services.KafkaProducer;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    public String senMessage(@RequestBody Mensaje message) {
        kafkaProducer.sendMessage("modas", message);
        return "Mensaje enviado: " + message.toString();
    }

}
