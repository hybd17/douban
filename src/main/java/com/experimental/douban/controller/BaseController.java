package com.experimental.douban.controller;

import com.experimental.douban.controller.exception.*;
import com.experimental.douban.service.ex.*;
import com.experimental.douban.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**控制层类的基类*/
public class BaseController {
    public static final int win = 200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UserNameRepeated) {
            result.setState(300);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException) {
            result.setState(400);
            result.setMessage("产生未知异常导致注册失败");
        }else if(e instanceof UserNameNotFoundException){
            result.setState(500);
            result.setMessage("用户不存在");
        } else if (e instanceof PasswordWrongException) {
            result.setState(600);
            result.setMessage("输入密码不正确");
        } else if (e instanceof UpdateException) {
            result.setState(700);
            result.setMessage("更新数据时产生未知异常");
        }else if (e instanceof FileEmptyException){
            result.setState(8000);
            result.setMessage("表示上传文件为空错误");
        }else if (e instanceof FileSizeException){
            result.setState(8001);
            result.setMessage("表示上传文件超过限制错误");
        }else if (e instanceof FileStateException){
            result.setState(8002);
            result.setMessage("表示上传文件状态异常错误");
        }else if (e instanceof FileTypeException){
            result.setState(8003);
            result.setMessage("表示上传文件类型不符错误");
        }else if (e instanceof FileUploadIOException){
            result.setState(8004);
            result.setMessage("表示上传文件IO读取错误");
        } else if (e instanceof MovieNameNotFoundException) {
            result.setState(10000);
            result.setMessage("电影不存在的异常");
        } else if (e instanceof SearchFailException) {
            result.setState(10100);
            result.setMessage("搜索时产生未知异常");
        }
        return result;
    }

    public final Integer getUidFromSession(HttpSession session){
        String uidGet = session.getAttribute("uid").toString();
        return Integer.valueOf(uidGet);
    }
    public final String getUserNameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
    public final String getMovieNameFromSession(HttpSession session){
        return session.getAttribute("movieName").toString();
    }
    public final Integer getMovieUidFromSession(HttpSession session){
        String uidGet = session.getAttribute("mid").toString();
        return Integer.valueOf(uidGet);
    }
}
