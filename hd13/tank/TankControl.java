package hd13.tank;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.net.NetServer;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;

/**
 * Created with IntelliJ IDEA.
 * User: rwinzing
 * Date: 21.03.14
 * Time: 23:22
 * To change this template use File | Settings | File Templates.
 */
public class TankControl extends Verticle {


    @Override
    public void start() {
        final Logger logger = container.logger();
        NetServer server = vertx.createNetServer();

        server.connectHandler(new Handler<NetSocket>() {
            public void handle(NetSocket sock) {
                logger.info("A client has connected!");

                JsonObject event = new JsonObject();
                event.putString("event-type","mindstorm").putString("action", "connected");
                vertx.eventBus().send("hd13.eventlogger", event);
            }
        });

        String ip = container.env().get("OPENSHIFT_VERTX_IP");
        if (ip == null) {
            ip = "127.0.0.1";
        }

        server.listen(2000, ip, new AsyncResultHandler<NetServer>() {
            @Override
            public void handle(AsyncResult<NetServer> voidAsyncResult) {
                logger.info("Listen succeeded? " + voidAsyncResult.succeeded());
                if (voidAsyncResult.succeeded()) {
                    NetServer ns = voidAsyncResult.result();
                }
            }
        });
    }
}
