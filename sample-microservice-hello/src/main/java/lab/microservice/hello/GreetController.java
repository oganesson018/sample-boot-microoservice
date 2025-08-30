package lab.microservice.hello;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	// basic hello 
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		Message newMsg = new Message("test test", "system", LocalDate.now());
		kafkaTemplate.send("message",newMsg);
		return new ResponseEntity<String>(newMsg.getText(), HttpStatus.OK);
	}

}