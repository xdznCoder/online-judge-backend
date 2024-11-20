package cn.xdzn.oj.service.system.domain.notice.entity.vo;

import cn.xdzn.oj.service.system.infrastructure.enums.WebsocketEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author m
 * @date 2023/11/4
 * @Description
 */
@Data
@Accessors(chain = true)
public class WebsocketResult<T> {
    private Integer type;
    private Integer state;
    private String message;
    private Long time = System.currentTimeMillis();
    private T data;

    public WebsocketResult() {
        // do nothing
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return @return {@link WebsocketResult }<{@link T }>
     * @date 2023/11/04
     * @author fate
     * @see T
     * @see WebsocketResult<T>
     */
    public static <T> WebsocketResult<T> success(T data) {
        var result = new WebsocketResult<T>();
        result.setData(data);
        return result;
    }

    /**
     * 成功
     *
     * @return @return {@link WebsocketResult }<{@link T }>
     * @date 2023/11/04
     * @author fate
     * @see WebsocketResult<T>
     */
    public static <T> WebsocketResult<T> success() {
        return new WebsocketResult<>();
    }

    /**
     * 失败
     *
     * @param message 消息
     * @return @return {@link WebsocketResult }<{@link T }>
     * @date 2023/11/04
     * @author fate
     * @see String
     * @see WebsocketResult<T>
     */
    public static <T> WebsocketResult<T> fail(String message) {
        var result = new WebsocketResult<T>();
        result.setMessage(message);
        result.setState(0);
        return result;
    }

    /**
     * 成功
     * 需要指出state
     *
     * @param websocketEnum websocket枚举
     * @return @return {@link WebsocketResult }<{@link T }>
     * @date 2023/11/04
     * @author fate
     * @see WebsocketEnum
     * @see WebsocketResult<T>
     */
    public static <T> WebsocketResult<T> success(WebsocketEnum websocketEnum) {
        return new WebsocketResult<T>().setMessage(websocketEnum.getMessage()).setType(websocketEnum.getType()).setState(1);
    }
}
