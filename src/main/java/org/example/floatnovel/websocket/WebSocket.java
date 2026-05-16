package org.example.floatnovel.websocket;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WebSocket {

    @RabbitListener(queues = "comment.queue")
    public void test(String msg) {
        System.out.println("收到消息：" + msg);
    }

}
