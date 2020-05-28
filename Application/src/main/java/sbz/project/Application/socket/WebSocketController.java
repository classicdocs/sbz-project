package sbz.project.Application.socket;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

    @Autowired
    private MessageProducer messageProducer;

    @MessageMapping("/websocket/topic")
    @SendTo("/websocket/topic")
    public void topic(
            Message message)
            throws Exception {
        messageProducer.sendMessage(message);
    }

    @GetMapping("/socket/test")
    public ResponseEntity testSocket() {
        messageProducer.sendMessage(new Message("CAO SVETE"));
        return new ResponseEntity(HttpStatus.OK);
    }
}
