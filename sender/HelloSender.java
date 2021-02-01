package guru.springframework.sfgjms.sender;

import guru.springframework.sfgjms.model.HelloWorldMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static guru.springframework.sfgjms.config.JmsConfig.MY_QUEUE;

@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    public HelloSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 2000) // sendMessage() will be called each 2 seconds
    public void sendMessage() {

        System.out.println("Sending message.");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(MY_QUEUE, message);

        System.out.println("Message sent.");

    }
}
