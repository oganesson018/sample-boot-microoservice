package lab.microservice.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class ReceiverComponent {
	Logger LOG = LoggerFactory.getLogger(ReceiverComponent.class);

	@KafkaListener(topics = "message")
	void listener(Message message) {
		LOG.info(message.getText() + " " + message.getAuthor());

	}
}