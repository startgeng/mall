package com.imooc.mall.exception;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseVo UserLoginException(){
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }
}
