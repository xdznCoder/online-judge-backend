//package cn.xdzn.oj.service.system.infrastructure.server;
//
//import cn.dev33.satoken.stp.StpUtil;
//import cn.hutool.json.JSONUtil;
//import cn.xdzn.oj.service.system.infrastructure.config.WebSocketConfig;
//import cn.xdzn.oj.service.system.infrastructure.enums.WebsocketEnum;
//import cn.xdzn.oj.service.system.domain.notice.entity.vo.WebsocketResult;
//import jakarta.websocket.*;
//import jakarta.websocket.server.ServerEndpoint;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Slf4j
//@Component
//@Controller
//@ServerEndpoint(value = "/ws/user/front", configurator = WebSocketConfig.class)
//public class WebSocketFrontServer {
//
//    /**
//     * 在线计数
//     */
//    private static final AtomicLong ONLINE_COUNT = new AtomicLong(0);
//    /**
//     * web套接字服务器映射
//     */
//    private static final Map<Long, WebSocketFrontServer> WEB_SOCKET_SERVER_MAP = new ConcurrentHashMap<>();
//    /**
//     * 会话
//     */
//    private Session session;
//    /**
//     * 用户id
//     */
//    private Long uid;
//
//    /**
//     * 广播消息
//     *
//     * @param websocketResult websocket结果
//     * @date 2023/11/04
//     * @author fate
//     * @see WebsocketResult <R>
//     */
//    public static <R> void broadcastMessages(WebsocketResult<R> websocketResult) {
//        WEB_SOCKET_SERVER_MAP.forEach((id, server) -> server.sendMessage(websocketResult));
//    }
//
//    /**
//     * 将消息发送到
//     *
//     * @param websocketResult websocket结果
//     * @date 2023/11/04
//     * @author fate
//     * @see WebsocketResult<R>
//     */
//    public static <R> void sendMessagesTo(List<Long> uid, WebsocketResult<R> websocketResult) {
//        for(Long id : uid){
//            var webSocketServer = WEB_SOCKET_SERVER_MAP.get(id);
//        if (webSocketServer != null) {
//            webSocketServer.sendMessage(websocketResult);
//        }
//        }
//    }
//
//    /**
//     * 获取在线人数
//     *
//     * @return @return {@link Long }
//     * @date 2023/11/04
//     * @author fate
//     * @see Long
//     */
//    public static Long getOnlineCount() {
//        return ONLINE_COUNT.get();
//    }
//
//    public static Set<Long> getOnlineUserId() {
//        return WEB_SOCKET_SERVER_MAP.keySet();
//    }
//
//
//    /**
//     * 打开时
//     *
//     * @param session 当前连接的会话
//     * @date 2023/11/04
//     * @author fate
//     * @see Session
//     */
//    @OnOpen
//    public void onOpen(Session session) {
//        log.info("有一连接开启！当前连接数为{}", ONLINE_COUNT.incrementAndGet());
//        var token = session.getQueryString().replace("swpu_token=", "");
//        var loginId = StpUtil.getLoginIdByToken(token);
//        this.session = session;
//        var uid = (loginId == null) ? 0L : Long.parseLong(loginId.toString());
//        if (uid >= 0) {
//            this.uid = uid;
//            WEB_SOCKET_SERVER_MAP.put(uid, this);
//            log.info("用户[{}]上线", uid);
//        } else {
//            close();
//        }
//    }
//
//    /**
//     * 关
//     *
//     * @date 2023/11/04
//     * @author fate
//     * @see WebsocketEnum
//     */
//    private void close() {
//        try {
//            this.sendMessage(WebsocketResult.success(WebsocketEnum.NOT_LOGIN).setState(0));
//            session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY, WebsocketEnum.NOT_LOGIN.getMessage()));
//            log.info("主动关闭连接{}", WebsocketEnum.NOT_LOGIN.getMessage());
//        } catch (IOException e) {
//            log.error("Websocket关闭错误");
//        }
//    }
//
//    /**
//     * 发送消息
//     *
//     * @param websocketResult websocket结果
//     * @date 2023/11/04
//     * @author fate
//     * @see WebsocketResult<R>
//     */
//    public <R> void sendMessage(WebsocketResult<R> websocketResult) {
//        try {
//            var jsonStr = JSONUtil.toJsonStr(websocketResult);
//            this.session.getBasicRemote().sendText(jsonStr);
//        } catch (IOException e) {
//            log.error("向用户:{}发送消息:{}时发生了错误", uid, websocketResult);
//        }
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息
//     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//            //如果是ping，则回复存活
//        if (WebsocketEnum.PING_MESSAGE.equals(message)) {
//            sendMessage(WebsocketResult.success(WebsocketEnum.PING).setState(1));
//        }
//    }
//
//    /**
//     * 关闭时
//     * @date 2023/11/04
//     * @author fate
//     */
//    @OnClose
//    public void onClose() {
//        if (uid != null) {
//            WEB_SOCKET_SERVER_MAP.remove(uid);
//        }
//        log.info("有一连接关闭！当前连接数为{}", ONLINE_COUNT.decrementAndGet());
//    }
//
//    /**
//     * 出现错误时
//     *
//     * @param session 会话
//     * @param error   错误
//     * @date 2023/11/04
//     * @author fate
//     * @see Session
//     * @see Throwable
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("websocket发生错误");
//    }
//}
