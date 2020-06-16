package vn.edu.hust.pthtwat.pthtwat.response;

public class BaseResponse {
    protected Boolean isSuccess;
    protected String message;
    protected int code;
    private Object data;

    public BaseResponse(){
        setIsSuccess(Boolean.TRUE);
    }

    public BaseResponse(String message, int code) {
        setIsSuccess(Boolean.FALSE);
        setMessage(message);
        setCode(code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getIsSuccess() {
        return isSuccess.booleanValue();
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toJson() {
        return " { \"message\" :"  + "\"" + message + "\" }";
    }
}