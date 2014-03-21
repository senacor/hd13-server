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

        JsonObject config = new JsonObject();
        config.putString("host", host)
                .putNumber("port", Long.valueOf(port))
                .putString("db_name", "server")
                .putString("username", "admin")
                .putString("password", "1Nq6LrahSJds");

        container.deployModule("io.vertx~mod-mongo-persistor~2.1.0", config);

        final EventBus eb = vertx.eventBus();

        Handler<Message<JsonObject>> myHandler = new Handler<Message<JsonObject>>() {
            public void handle(Message<JsonObject> message) {
                System.out.println("I received a message " + message.body());
                JsonObject saveMessage = new JsonObject();
                saveMessage
                    .putString("action", "save")
                    .putString("collection", "access")
                    .putObject("document", message.body());

                eb.send("vertx.mongopersistor", saveMessage);
            }
        };

        eb.registerHandler("hd13.userregistry", myHandler);
    }
}