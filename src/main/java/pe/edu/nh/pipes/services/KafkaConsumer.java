package pe.edu.nh.pipes.services;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pe.edu.nh.pipes.model.Mensaje;


import java.util.function.Supplier;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "modas", groupId = "group_id")
    public void listen(Mensaje message) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(java.time.Duration.ofSeconds(10))
                .slidingWindowSize(5)
                .build();

        CircuitBreaker circuitBreaker = CircuitBreaker.of("miCircuitBraker", circuitBreakerConfig);

        //La llamada al API de la BD
        Supplier<String> supplier = () ->{
            if(Math.random() > 0.8){
                throw new RuntimeException("Error en la llamada al API de la BD");
            }
            return "Respuesta del API de la BD";
        };

        Supplier<String> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);

        for(int i = 0; i < 20; i++){
            try{
                String result = decoratedSupplier.get();
                System.out.println("Resultado: " + result);
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Mensaje Recibido del Consumer: " + message.toString());
    }
}
