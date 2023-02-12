package com.experimental.douban.controller;

import com.experimental.douban.controller.exception.*;
import com.experimental.douban.entity.User;
import com.experimental.douban.service.IUserService;

import com.experimental.douban.util.JsonResult;
import com.experimental.douban.vo.HaveWatchVO;
import com.experimental.douban.vo.WantWatchVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController{
    @Resource
    private IUserService userService;

    @RequestMapping("/enroll")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result = new JsonResult<>();
        userService.reg(user);
        return new JsonResult<>(win,"注册成功");
    }
    @RequestMapping("/login")
    public JsonResult<User> login(User user, HttpSession session){
        User data = userService.login(user);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        /**
         * 测试session
        System.out.println(getUidFromSession(session));
        System.out.println(getUserNameFromSession(session));
         */
        return new JsonResult<User>(win,data);
    }
    @RequestMapping("/resetPassword")
    public JsonResult<Void> updatePassword(@RequestParam("oldPassword") String oldPwd,
                                           @RequestParam("newPassword") String newPwd,
                                           HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUserNameFromSession(session);
        userService.updatePassword(oldPwd,newPwd,uid,username);
        session.setAttribute("uid",null);
        return new JsonResult<>(win);
    }
    @RequestMapping("/findUser")
    //主要配合更改用户数据使用
    public JsonResult<User> getUserByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        User user = userService.findUserByUid(uid);
        //防止数据冗余进行部分传输
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUid(user.getUid());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setAvatar(user.getAvatar());
        return new JsonResult<>(win,newUser);
    }
    @RequestMapping("/updateInfo")
    public JsonResult<User> updateInfo(String phone,String email,Integer gender,HttpSession session){
        String username = getUserNameFromSession(session);
        Integer uid = getUidFromSession(session);
        userService.userUpdateInfo(phone,email,gender,username,uid);
        User user = userService.findUserByUid(uid);
        return new JsonResult<>(win,user);
    }
    /**
     * 设置上传头像文件限制
     * 先获取存储文件的文件夹的路径，判断存储文件的文件夹是否存在，不存在则创建,最后再将文件名和文件夹路径进行组合文件的保存路径
     * 进行上传
     * */
    private static final Integer fileMaxSize = 10 * 1024 * 1024;
    private static final List<String> FileType = new ArrayList<>();
    static {
        FileType.add("image/jpeg");
        FileType.add("image/png");
        FileType.add("image/bmp");
        FileType.add("image/gif");
    }
    @RequestMapping("/userUpLoadAvatar")
    public JsonResult<String> upLoadAvatar(HttpSession session, MultipartFile file){
        if(file.isEmpty()){
            throw new FileEmptyException("上传文件为空");
        }
        if (file.getSize()>fileMaxSize){
            throw new FileSizeException("上传文件过大");
        }
        if (!FileType.contains(file.getContentType())){
            throw new FileTypeException("上传文件格式不正确");
        }
        String parent = session.getServletContext().getRealPath("/upLoad");
        System.out.println(parent);//测试数据
        File dir = new File(parent);
        //检测是否存在
        if(!dir.exists()){
            dir.mkdir();
        }
        //避免名称覆盖，随机生成名称
        String originalFileName = file.getOriginalFilename();
        System.out.println(originalFileName);//测试数据
        String lastName = originalFileName.substring(originalFileName.lastIndexOf("."));//获取索引进而获取名称后缀
        String fileName = UUID.randomUUID().toString().toUpperCase()+lastName;
        File dest = new File(dir,fileName);
        try {
            file.transferTo(dest);
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");//先捕获优先级低的，让异常分开
        }catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid = getUidFromSession(session);
        String avatar = "/upload/"+fileName;
        userService.userUpImage(avatar,uid);
        return new JsonResult<>(win,avatar);
    }

    @RequestMapping("/HaveWatch")
    public JsonResult<List<HaveWatchVO>> findHVOByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<HaveWatchVO> hvoByUid = userService.getHVOByUid(uid);
        return new JsonResult<>(win,hvoByUid);
    }
    @RequestMapping("/WantWatch")
    public JsonResult<List<WantWatchVO>> findWVOByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<WantWatchVO> wvoByUid = userService.getWVOByUid(uid);
        return new JsonResult<>(win,wvoByUid);
    }
}
