package com.along.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResult {
    private LocalDateTime dt=LocalDateTime.now();
    private String code;
    private String desc;

    public ErrorResult(ErrorCode errorCode){
        this.code=errorCode.getCode();
        this.desc=errorCode.getDesc();
    }
}
