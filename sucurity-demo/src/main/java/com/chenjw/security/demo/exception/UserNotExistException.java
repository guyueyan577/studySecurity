/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: chenjianwei
 * @date: 2020-03-01
 * @version V1.0
 */
package com.chenjw.security.demo.exception;

/**@Description: 自定义一个异常信息
 * @date: 2020-03-01
 */
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 3806646633448261548L;

    String id;

    public UserNotExistException(String id) {
        super("user not exception!");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
