package io.github.qxqrose;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: qiu
 * 2021/8/5
 */
@Data
@AllArgsConstructor
public class Verification {
    /**
     * 房间门id
     */
    String doorId;

    /**
     * 验证内容
     */
    String content;

    /**
     * 验证类型
     */
    String type;
}
