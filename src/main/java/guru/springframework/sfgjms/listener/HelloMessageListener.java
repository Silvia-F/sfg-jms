package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static guru.springframework.sfgjms.config.JmsConfig.MY_QUEUE;

@Component
public class HelloMessageListener {

    @JmsListener(destination = MY_QUEUE) // Queue to which we want to listen to
    public void listen(@Payload HelloWorldMessage helloWorldMessage, // Specifies the message should be deserialized into an HelloWorldMessage
                       MessageHeaders headers, // Get the message headers (jms properties)
                       Message message) {

        System.out.println("I got a message.");
        System.out.println(helloWorldMessage);

        //Since jms is transactional, if an exception is thrown while the client is processing the message, this message will be redelivered
        //throw new RuntimeException();
    }
}
