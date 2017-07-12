package liwei.com.newsapp.entity;

import java.io.Serializable;

/**
 * Created by liwei on 2017/7/11.
 */
public class ParentEntity implements Serializable{

    private String reason;
    private int error_code;

    public ParentEntity() {
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {

        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public ParentEntity(String reason, int error_code) {

        this.reason = reason;
        this.error_code = error_code;
    }
}
