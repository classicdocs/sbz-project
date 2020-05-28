package sbz.project.Application.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Component
public class MessageProducer {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendMessage(Message message) {
        this.template.convertAndSend("/topic", message);
    }
}
