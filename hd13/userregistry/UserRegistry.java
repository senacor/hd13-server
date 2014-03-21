package hd13.userregistry;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

public class UserRegistry extends Verticle {

    /**
     *
     * admin / 1Nq6LrahSJds
     * db: server
     *
     * mongodb://$OPENSHIFT_MONGODB_DB_HOST:$OPENSHIFT_MONGODB_DB_PORT/
     *
     *
     */
    public void start() {
        System.out.println("starting UserRegistry ...");

        String host = container.env().get("OPENSHIFT_MONGODB_DB_HOST");
        String port = container.env().get("OPENSHIFT_MONGODB_DB_PORT");

        System.out.println("port = " + port);
        System.out.println("host = " + host);

        JsonObject config = new JsonObject("{" +
                // "'address': 'test.my_persistor', " +
                "\"host\": \""+host+"\","+
                "\"port\": "+port+","+
                "\"pool_size\": 20, "+
                "\"db_name\": \"server\""+
                "}");

        container.deployModule("io.vertx~mod-mongo-persistor~2.1.0", config);

        EventBus eb = vertx.eventBus();

        Handler<Message> myHandler = new Handler<Message>() {
            public void handle(Message message) {
                System.out.println("I received a message " + message.body());
            }
        };

        eb.registerHandler("hd13.userregistry", myHandler);
    }
}