package com.along.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    REQUEST_ERR("ERROR_CODE_0000","잘못된 요청입니다."),
    EMPTY_ERR("ERROR_CODE_0001","NULL이거나 공백만 존재하는 문자열입니다."),
    FORMAT_ERR("ERROR_CODE_0002","올바른 형식을 입력해주세요."),
    SIZE_ERR("ERROR_CODE_0003","1~20자만 입력 가능합니다.");

    private String code;
    private String desc;

    ErrorCode(String code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
