package lab.microservice.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class GreetController {

    private static final Logger logger = LoggerFactory.getLogger(GreetController.class);

    @Autowired
    private UserServiceProxy proxy;

    // basic hello 
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/api/greet/{id}")
    public ResponseEntity<String> greet(@PathVariable Long id) {

        // call user-service via Feign (with load balancing)
        User user = proxy.retrieveUsername(id);

        LocalTime currentTime = LocalTime.now();
        String greeting = "Hello!";
        if (currentTime.isBefore(LocalTime.NOON)) {
            greeting = "Good morning!";
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
            greeting = "Good afternoon!";
        } else {
            greeting = "Good evening!";
        }
        
        String message = String.format("%s %s! (Called user-service instance on port %d)", 
                                     greeting, user.getName(), user.getPort());
        logger.info("RESPONSE: {} - Load balanced to port {}", message, user.getPort());

        return ResponseEntity.ok(message);
    }
}