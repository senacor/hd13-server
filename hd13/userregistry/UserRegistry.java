package hd13.userregistry;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class UserRegistry extends Verticle {

    public void start() {
        container.deployModule("io.vertx~mod-mongo-persistor~2.1.0");

        EventBus eb = vertx.eventBus();

        Handler<Message> myHandler = new Handler<Message>() {
            public void handle(Message message) {
                System.out.println("I received a message " + message.body());
            }
        };

        eb.registerHandler("devcon.2013.1", myHandler);
    }
}