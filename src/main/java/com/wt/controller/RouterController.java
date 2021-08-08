package com.wt.controller;

import com.wt.pojo.*;
import com.wt.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RouterController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("animeServiceImpl")
    AnimeService animeService;

    @Autowired
    @Qualifier("comicServiceImpl")
    ComicService comicService;

    @Autowired
    @Qualifier("watchServiceImpl")
    WatchService watchService;

    @Autowired
    @Qualifier("readServiceImpl")
    ReadService readService;

    @GetMapping({"/","/login"})
    public String toLoginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String toRegisterPage(){
        return "register";
    }

    @GetMapping("/addanime")
    public String toAddAnimePage(){
        return "addanime";
    }

    @GetMapping("/addcomic")
    public String toAddComicPage(){
        return "addcomic";
    }

    @PostMapping("/login")
    public String login(@PathParam("username") String username,
                        @PathParam("password") String password,
                        Model model){
        //不安全验证登录方法
//        //根据用户名或查库判断用户是否存在
//        User user = userService.getUserByUsername(username);
//
//        //判断用户是否存在
//        if (user == null){
//            model.addAttribute("mag01","用户不存在");
//            return "login";
//        }else {
//            //用户存在，判断输入的密码是否正确
//            if(password.equals(user.getPassword())){
//                return "redirect:/animeschedule";
//            }
//            else {
//                model.addAttribute("mag02","密码错误");
//                return "login";
//            }
//        }

        //安全验证登录方法
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据成token对象
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //检查用户登录数据
        try{
            subject.login(token);
            //如果成功登录，将用户名存入session
            User user = userService.getUserByUsername(username);
            //向session中存入当前用户名与id
            subject.getSession().setAttribute("username",user.getUsername());
            subject.getSession().setAttribute("userId",user.getId());

            return "redirect:/animeschedule";
        }catch(UnknownAccountException uae){
            model.addAttribute("msg01","用户不存在");
            return "login";
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg02","密码错误");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@PathParam("username") String username,
                           @PathParam("password") String password,
                           @PathParam("passwordConfirmation") String passwordConfirmation,
                           Model model){
        //判断是否输入用户名密码
        if (username.equals("")){
            model.addAttribute("msg03","请输入用户名");
            return "register";
        }
        if (password.equals("")){
            model.addAttribute("msg06","请输入密码");
            return "register";
        }

        //判断此用户名是否已被注册
        User user = userService.getUserByUsername(username);
        if (user != null){
            model.addAttribute("msg03","用户名已存在");
            return "register";
        }else {
            //用户名未被注册，判断两次输入的密码是否一致
            if (!password.equals(passwordConfirmation)){
                model.addAttribute("msg04","两次输入的密码不一致");
                return "register";
            }else {
                userService.registerAnUser(username,password);
                model.addAttribute("msg05","注册成功，请登录");
                return "login";
            }
        }
    }

    @PostMapping("/addanime")
    public String addAnime(Anime anime,@PathParam("date") String date,
                           Model model) throws ParseException {
        //日期的处理
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date playDate = format.parse(date);
        anime.setPlayDate(playDate);

        //找备案号相同的记录，若能找到，则判断为此条目已存在
        if(animeService.getAnimeByRecordNumber(anime.getRecordNumber()) == null){
            animeService.addAnAnime(anime);
            return "redirect:/adminanime";
        }else {
            model.addAttribute("msg22","插入的动画已经存在");
            return "addanime";
        }
    }

    @PostMapping("/addcomic")
    public String addComic(Comic comic, @PathParam("date") String date,
                           Model model) throws ParseException {
        //日期的处理
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date publicationDate = format.parse(date);
        comic.setPublicationDate(publicationDate);

        //找isbn相同的记录，若能找到，则判断为此条目已被插入
        if (comicService.getComicByISBN(comic.getIsbn()) == null){
            comicService.addAComic(comic);
            return "redirect:/admincomic";
        }else {
            model.addAttribute("msg21","插入的漫画已存在");
            return "addcomic";
        }
    }

    @GetMapping("/adminanime")
    public String animeAdmin(Model model){

        //查询所有动画信息带回前台
        List<Anime> allAnime = animeService.getAllAnime();

        model.addAttribute("allAnime",allAnime);

        return "adminanime";
    }

    @GetMapping("/updateAnimeInfo/{animeId}")
    public String toUpdateAnimeInfoPage(@PathVariable Integer animeId,
                                        Model model){
        //根据id得到anime信息，送到前台
        Anime anime = animeService.getAnimeById(animeId);
        model.addAttribute("anime",anime);

        return "updateanimeinfo";
    }

    @PostMapping("/updateAnimeInfo")
    public String updateAnimeInfo(Anime anime,
                                  @PathParam("date") String date) throws ParseException {
        //日期的处理
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date playDate = format.parse(date);
        anime.setPlayDate(playDate);

        //更新动画条目的信息
        animeService.updateAnimeInfo(anime);

        return "redirect:/adminanime";
    }

    @GetMapping("/deleteAnAnime/{animeId}")
    public String deleteAnAnime(@PathVariable Integer animeId){
        //要判断此动画是否被用户收藏以确定是否能删除
        //根据animeId查watch表，判断此动画是否被收藏
        if (watchService.getWatchInfoByAnimeId(animeId).isEmpty()){
            //可以删除
            animeService.deleteAnAnime(animeId);
            return "redirect:/adminanime";
        }else {
            return "/error/limit";
        }
    }

    @PostMapping("/userQueryAnime")
    public String queryAnime(@PathParam("userQueryInput") String userQueryInput,
                             Model model){
        //根据用户输入模糊查询company,director,animeName字段
        List<Anime> animeList = animeService.getAnimeByUserInput(userQueryInput);
        model.addAttribute("allAnime",animeList);

        return "adminanime";
    }

    @GetMapping("/animeschedule")
    public String toAnimeSchedulePage(Model model){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id查库得到进度信息，送到前台
        List<Watch> watches = watchService.getWatchInfoByUserId(id);
        model.addAttribute("watches",watches);

//        //将watchInfo和动画用Map绑定起来，再把map送到前台
//        Map<Integer,Anime> animeWatchMap = new HashMap<>();
//        for (Watch watch : watches){
//            //根据watch得到动画id，通过动画id得到动画对象，再将动画对象和watchId绑定起来
//            Anime anime = animeService.getAnimeById(watch.getAnimeId());
//            animeWatchMap.put(watch.getWatchId(),anime);
//        }

        //发现thymeleaf有点问题，于是只能把想要的信息反别放入几个map
        Map<Integer,String> posterMap = new HashMap<>();
        Map<Integer,String> animeNameMap = new HashMap<>();
        Map<Integer,Integer> episodeMap = new HashMap<>();
        for (Watch watch : watches){
            //根据watch得到动画id，通过动画id得到动画对象，再将动画对象和watchId绑定起来
            Anime anime = animeService.getAnimeById(watch.getAnimeId());
            posterMap.put(watch.getWatchId(),anime.getPoster());
            animeNameMap.put(watch.getWatchId(),anime.getAnimeName());
            episodeMap.put(watch.getWatchId(),anime.getEpisode());
        }
        model.addAttribute("posterMap",posterMap);
        model.addAttribute("animeNameMap",animeNameMap);
        model.addAttribute("episodeMap",episodeMap);

        return "animeschedule";
    }

    @GetMapping("/animeschedule/watching")
    public String toWatchingPage(Model model){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id，当前需求状态=1查库得到信息
        List<Watch> watches = watchService.getWatchInfoByUserIdAndState(id,1);
        model.addAttribute("watches",watches);

        Map<Integer,String> posterMap = new HashMap<>();
        Map<Integer,String> animeNameMap = new HashMap<>();
        Map<Integer,Integer> episodeMap = new HashMap<>();
        for (Watch watch : watches){
            //根据watch得到动画id，通过动画id得到动画对象，再将动画对象和watchId绑定起来
            Anime anime = animeService.getAnimeById(watch.getAnimeId());
            posterMap.put(watch.getWatchId(),anime.getPoster());
            animeNameMap.put(watch.getWatchId(),anime.getAnimeName());
            episodeMap.put(watch.getWatchId(),anime.getEpisode());
        }
        model.addAttribute("posterMap",posterMap);
        model.addAttribute("animeNameMap",animeNameMap);
        model.addAttribute("episodeMap",episodeMap);

        return "animeschedule";
    }

    @GetMapping("/animeschedule/watched")
    public String toWatchedPage(Model model){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id，当前需求状态=1查库得到信息
        List<Watch> watches = watchService.getWatchInfoByUserIdAndState(id,2);
        model.addAttribute("watches",watches);

        Map<Integer,String> posterMap = new HashMap<>();
        Map<Integer,String> animeNameMap = new HashMap<>();
        Map<Integer,Integer> episodeMap = new HashMap<>();
        for (Watch watch : watches){
            //根据watch得到动画id，通过动画id得到动画对象，再将动画对象和watchId绑定起来
            Anime anime = animeService.getAnimeById(watch.getAnimeId());
            posterMap.put(watch.getWatchId(),anime.getPoster());
            animeNameMap.put(watch.getWatchId(),anime.getAnimeName());
            episodeMap.put(watch.getWatchId(),anime.getEpisode());
        }
        model.addAttribute("posterMap",posterMap);
        model.addAttribute("animeNameMap",animeNameMap);
        model.addAttribute("episodeMap",episodeMap);

        return "animeschedule";
    }

    @GetMapping("/animeschedule/add")
    public String toAddAnimeSchedulePage(Model model){
        //查询所有动画信息带回前台
        List<Anime> allAnime = animeService.getAllAnime();

        model.addAttribute("allAnime",allAnime);

        return "addanimeschdule";
    }

    @PostMapping("/userQueryAnime/schedule")
    public String queryAnimeSchedule(@PathParam("userQueryInput") String userQueryInput,
                             Model model){
        //根据用户输入模糊查询company,director,animeName字段
        List<Anime> animeList = animeService.getAnimeByUserInput(userQueryInput);
        model.addAttribute("allAnime",animeList);

        return "addanimeschdule";
    }

    @GetMapping("/addanimeschdule/{animeId}")
    public String addAnimeSchedule(@PathVariable Integer animeId){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据animeId和userId插入一条watch数据
        watchService.insertAWatchInfoByAW(animeId,id);

        return "redirect:/animeschedule";
    }

    @GetMapping("/animeschedule/delete/{watchId}")
    public String deleteAWatchInfo(@PathVariable Integer watchId){
        //根据watchId删除watch信息
        watchService.deleteAWatchInfoByWatchId(watchId);

        return "redirect:/animeschedule";
    }

    @GetMapping("/animeschedule/update/{watchId}")
    public String toUpdateAWatchInfoPage(@PathVariable Integer watchId,
                                         Model model){
        //根据watchId拿到这条信息，送到前端
        Watch watch = watchService.getWatchInfoByWatchId(watchId);
        model.addAttribute("watch",watch);

        return "updatewatchinfo";
    }

    @PostMapping("/animeschedule/update")
    public String updateWatchInfo(Watch watch){
        //修改此条watch的信息
        watchService.updateWatchInfo(watch);

        return "redirect:/animeschedule";
    }

    @GetMapping("/admincomic")
    public String animeComic(Model model){

        //查询所有漫画信息带回前台
        List<Comic> allComic = comicService.getAllComic();
        model.addAttribute("allComic",allComic);

        return "admincomic";
    }

    @GetMapping("/deleteAComic/{comicId}")
    public String deleteAComic(@PathVariable Integer comicId){
        //要判断此漫画是否被用户收藏以确定是否能删除
        if (readService.getReadInfoByComicId(comicId).isEmpty()){
            comicService.deleteAComicByComicId(comicId);
            return "redirect:/admincomic";
        }else {
            return "/error/limit";
        }
    }

    @GetMapping("/updateComicInfo/{comicId}")
    public String toUpdateComicInfoPage(@PathVariable Integer comicId,
                                        Model model){
        //根据id得到comic信息
        Comic comic = comicService.getComicByComicId(comicId);
        model.addAttribute("comic",comic);

        return "updatecomicinfo";
    }

    @PostMapping("/updateComicInfo")
    public String updateComicInfo(@PathParam("date") String date,
                                  Comic comic) throws ParseException {
        //日期的处理
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date publicationDate = format.parse(date);
        comic.setPublicationDate(publicationDate);

        //修改信息
        comicService.updateComicInfo(comic);

        return "redirect:/admincomic";
    }

    @PostMapping("/userQueryComic")
    public String queryComic(@PathParam("userQueryInput") String userQueryInput,
                             Model model){
        //根据用户输入模糊查询
        List<Comic> comicList = comicService.getComicByUserInput(userQueryInput);
        model.addAttribute("allComic",comicList);

        return "admincomic";
    }

    @GetMapping("/comicschedule")
    public String toComicSchedulePage(Model model){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id查库得到进度信息，送到前台
        List<Read> reads = readService.getReadInfoByUserId(id);
        model.addAttribute("reads",reads);

        //根据read中的comicId得到comic的cover，name，page信息,将这些信息与readId绑定在一起
        Map<Integer,String> coverMap = new HashMap<>();
        Map<Integer,String> comicNameMap = new HashMap<>();
        Map<Integer,Integer> pageMap = new HashMap<>();
        for(Read read : reads){
            Comic comic = comicService.getComicByComicId(read.getComicId());
            coverMap.put(read.getReadId(),comic.getCover());
            comicNameMap.put(read.getReadId(),comic.getComicName());
            pageMap.put(read.getReadId(),comic.getPage());
        }
        model.addAttribute("coverMap",coverMap);
        model.addAttribute("comicNameMap",comicNameMap);
        model.addAttribute("pageMap",pageMap);

        return "comicschedule";
    }

    @GetMapping("/comicschedule/watching")
    public String toComicWatchingPage(Model model){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id和状态得到read信息
        List<Read> reads = readService.getReadInfoByUserIdAndState(id,1);
        model.addAttribute("reads",reads);

        //根据read中的comicId得到comic的cover，name，page信息,将这些信息与readId绑定在一起
        Map<Integer,String> coverMap = new HashMap<>();
        Map<Integer,String> comicNameMap = new HashMap<>();
        Map<Integer,Integer> pageMap = new HashMap<>();
        for(Read read : reads){
            Comic comic = comicService.getComicByComicId(read.getComicId());
            coverMap.put(read.getReadId(),comic.getCover());
            comicNameMap.put(read.getReadId(),comic.getComicName());
            pageMap.put(read.getReadId(),comic.getPage());
        }
        model.addAttribute("coverMap",coverMap);
        model.addAttribute("comicNameMap",comicNameMap);
        model.addAttribute("pageMap",pageMap);

        return "comicschedule";
    }

    @GetMapping("/comicschedule/watched")
    public String toComicWatchedPage(Model model){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id和状态得到read信息
        List<Read> reads = readService.getReadInfoByUserIdAndState(id,2);
        model.addAttribute("reads",reads);

        //根据read中的comicId得到comic的cover，name，page信息,将这些信息与readId绑定在一起
        Map<Integer,String> coverMap = new HashMap<>();
        Map<Integer,String> comicNameMap = new HashMap<>();
        Map<Integer,Integer> pageMap = new HashMap<>();
        for(Read read : reads){
            Comic comic = comicService.getComicByComicId(read.getComicId());
            coverMap.put(read.getReadId(),comic.getCover());
            comicNameMap.put(read.getReadId(),comic.getComicName());
            pageMap.put(read.getReadId(),comic.getPage());
        }
        model.addAttribute("coverMap",coverMap);
        model.addAttribute("comicNameMap",comicNameMap);
        model.addAttribute("pageMap",pageMap);

        return "comicschedule";
    }

    @GetMapping("/comicschedule/add")
    public String toAddComicSchedulePage(Model model){
        List<Comic> allComic = comicService.getAllComic();
        model.addAttribute("allComic",allComic);

        return "addcomicschedule";
    }

    @PostMapping("/userQueryComic/schedule")
    public String queryComicSchedule(@PathParam("userQueryInput") String userQueryInput,
                                     Model model){
        List<Comic> comicList = comicService.getComicByUserInput(userQueryInput);
        model.addAttribute("allComic",comicList);

        return "addcomicschedule";
    }

    @GetMapping("/addcomicschdule/{comicId}")
    public String addComicSchedule(@PathVariable Integer comicId){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据comicId和userId插入一条read数据
        readService.insertAReadInfoByCU(comicId,id);

        return "redirect:/comicschedule";
    }

    @GetMapping("/comicschedule/delete/{readId}")
    public String deleteAReadInfo(@PathVariable Integer readId){
        //根据readId删除一条记录
        readService.deleteAReadInfoByReadId(readId);

        return "redirect:/comicschedule";
    }

    @GetMapping("/comicschedule/update/{readId}")
    public String toUpdateComicSchedulePage(@PathVariable Integer readId,
                                            Model model){
        //根据id查库拿到信息送到前台
        Read read = readService.getAReadInfoByReadId(readId);
        model.addAttribute("read",read);

        return "updatereadinfo";
    }

    @PostMapping("/comicschedule/update")
    public String updateComicSchedule(Read read){
        readService.updateReadInfo(read);

        return "redirect:/comicschedule";
    }

    @GetMapping("/adminuser")
    public String toUserAdminPage(Model model){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id得到用户信息
        User user = userService.getUserById(id);
        model.addAttribute("user",user);

        return "updateuserinfo";
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(User user,
                                 @PathParam("passwordConfirmation") String passwordConfirmation,
                                 Model model,
                                 @PathParam("date") String date) throws ParseException {
        //日期的处理
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = format.parse(date);
        user.setBirthday(birthday);

        //判断两次输入的密码是否一致
        if (!user.getPassword().equals(passwordConfirmation)){
            model.addAttribute("user",user);
            model.addAttribute("msg11","两次输入的密码不一致");
            return "updateuserinfo";
        }else {
            userService.updateUserInfo(user);
        }

        return "redirect:/animeschedule";
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();//先获取当前用户对象
        subject.logout();//执行登出

        return "redirect:/";
    }

    @GetMapping("/cancelaccount")
    public String cancelAccount(){
        //从session中取出用户id
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer id = (Integer) session.getAttribute("userId");

        //根据用户id删除用户的动画漫画进度记录
        readService.deleteReadInfosByUserId(id);
        watchService.deleteWatchInfosByUserId(id);

        //删除账户
        userService.deleteUserByUserId(id);

        return "redirect:/";
    }
}
