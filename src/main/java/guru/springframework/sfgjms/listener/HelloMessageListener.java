package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

import java.util.UUID;

import static guru.springframework.sfgjms.config.JmsConfig.MY_QUEUE;
import static guru.springframework.sfgjms.config.JmsConfig.MY_SEND_RCV_QUEUE;

@Component
@RequiredArgsConstructor
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = MY_QUEUE) // Queue to which we want to listen to
    public void listen(@Payload HelloWorldMessage helloWorldMessage, // Specifies the message should be deserialized into an HelloWorldMessage
                       MessageHeaders headers, // Get the message headers (jms properties)
                       Message message) {

        //Since jms is transactional, if an exception is thrown while the client is processing the message, this message will be redelivered
        //throw new RuntimeException();
    }

    @JmsListener(destination = MY_SEND_RCV_QUEUE)
    public void listenForHello(Message message) throws JMSException {

        HelloWorldMessage payload = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payload);
    }
}
