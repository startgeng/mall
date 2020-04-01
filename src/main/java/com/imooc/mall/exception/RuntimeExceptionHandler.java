package com.imooc.mall.exception;

import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

import static com.imooc.mall.enums.ResponseEnum.ERROR;

@ControllerAdvice
public class RuntimeExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseVo Handler(RuntimeException e){
//        return ResponseVo.error(ResponseEnum.ERROR,ResponseEnum.ERROR.getDesc());
//    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
//	@ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseVo handle(RuntimeException e) {
        return ResponseVo.error(ERROR, ERROR.getDesc());
    }

    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo userLoginException() {
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ResponseVo cartException(MethodArgumentNotValidException e,BindingResult bindingResult) {
//        return ResponseVo.error(ResponseEnum.ERROR,
//                bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
//    }
@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseBody
public ResponseVo notValidExceptionHandle(MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
//    Objects.requireNonNull(bindingResult.getFieldError());
    return ResponseVo.error(ResponseEnum.PARAM_ERROR,
            bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
}
}
