package data.model;

import lombok.Data;

public enum StatusResponse {
    SUCCESS("success"),
    ERROR("error");

    private String message;
    StatusResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
