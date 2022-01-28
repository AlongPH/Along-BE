package com.along.vo;

import com.along.error.ErrorResult;
import org.springframework.http.HttpStatus;

public class ResultVO {
    HttpStatus status;
    Object success;
    ErrorResult error;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getSuccess() {
        return success;
    }

    public void setSuccess(Object success) {
        this.success = success;
    }

    public ErrorResult getError() {
        return error;
    }

    public ErrorResult setError(ErrorResult error) {
        this.error = error;
        return null;
    }
}
