package vn.edu.hust.pthtwat.pthtwat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import vn.edu.hust.pthtwat.pthtwat.model.StompPrincipal;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * Set anonymous user (Principal) in WebSocket messages by using UUID
 * This is necessary to avoid broadcasting messages but sending them to specific user sessions
 */

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    private final static Logger log = LoggerFactory.getLogger(CustomHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        // generate user name by UUID
        log.info("attributes is : {}", attributes);
        return new StompPrincipal(UUID.randomUUID().toString());
    }

}