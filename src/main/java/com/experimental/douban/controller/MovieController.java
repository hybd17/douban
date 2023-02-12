package com.experimental.douban.controller;

import com.experimental.douban.entity.Movie;
import com.experimental.douban.service.IHaveWatchService;
import com.experimental.douban.service.IMovieService;
import com.experimental.douban.service.IWantWatchService;
import com.experimental.douban.service.ex.InsertException;
import com.experimental.douban.util.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequestMapping("/movie")
@RestController
public class MovieController extends BaseController{

    @Resource
    private IMovieService movieService;
    @Resource
    private IWantWatchService wantWatchService;
    @Resource
    private IHaveWatchService haveWatchService;

    @RequestMapping("/search")
    public JsonResult<Movie> findMovieByMovieName(String movieName,HttpSession session){
        Movie movie = movieService.findMovieByName(movieName);
        session.setAttribute("movieName",movieName);
        session.setAttribute("mid",movie.getMid());
        //测试session存储
//        System.out.println(getMovieNameFromSession(session));
//        System.out.println(getMovieUidFromSession(session));
        return new JsonResult<>(win,movie);
    }
    @RequestMapping("/wantWatch")
    public JsonResult<Void> insertWantWatchMovie(HttpSession session){
        String username = getUserNameFromSession(session);
        String movieName = getMovieNameFromSession(session);
        Integer result = wantWatchService.addToWantWatch(username, movieName);
        return new JsonResult<>(win,"注入成功");
    }
    @RequestMapping("/haveWatch")
    private JsonResult<Void> insertHaveWatchMovie(HttpSession session){
        String username = getUserNameFromSession(session);
        String movieName = getMovieNameFromSession(session);
        Integer result = haveWatchService.addToHaveWatch(username,movieName);
        return new JsonResult<>(win,"注入成功");
    }
}
