

package com.hx.response;

/**
 * Created by testuser on 17-3-23.
 */
public class CodeAndMessage {
    private int code;
    private String message;

    public CodeAndMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
