package com.along.error;

import com.along.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    /**
     * @Valid 유효성 체크 통과 못할 시 MethodArgumentNotValidException 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO notValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ResultVO resultVO=new ResultVO();
        resultVO.setStatus(HttpStatus.BAD_REQUEST);
        log.warn("notValidException 발생! url : {}, trace : {}",request.getRequestURI(),e.getStackTrace());

        String code="";
        String desc="";

        BindingResult bindingResult=e.getBindingResult();
        if(bindingResult.hasErrors()){
            String bindResultCode=bindingResult.getFieldError().getCode();
            switch(bindResultCode){
                case "NotNull":
                    resultVO.setError(new ErrorResult(ErrorCode.EMPTY_ERR));
                    break;
                case "NotEmpty":
                    resultVO.setError(new ErrorResult(ErrorCode.EMPTY_ERR));
                    break;
                case "Size":
                    resultVO.setError(new ErrorResult(ErrorCode.SIZE_ERR));
                    break;
                case "Pattern":
                    resultVO.setError(new ErrorResult(ErrorCode.FORMAT_ERR));
                    break;
            }
        }else{
            resultVO.setError(new ErrorResult(ErrorCode.REQUEST_ERR));
        }

        return resultVO;
    }
}
