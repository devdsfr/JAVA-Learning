package com.daniel.myapi.resources.exception;

public class StandartError {

    private Integer status;
    private Long timestamp;
    private String message;

    public StandartError(){
        super();
    }

    public StandartError(Integer status, Long timestamp, String message){
        super();
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
