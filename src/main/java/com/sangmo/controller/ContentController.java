package com.sangmo.controller;


import com.sangmo.domain.Content;
import com.sangmo.domain.User;
import com.sangmo.service.ContentService;
import com.sangmo.util.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class ContentController {

    private ContentService contentService;

    @Autowired
    public void setContentService(ContentService contentService){
        this.contentService = contentService;
    }

    @RequestMapping(value = "/show")
    public String showContent(HttpServletRequest request, HttpServletResponse response,
                              ModelMap modelMap,@RequestParam("id") int id){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        Content content = contentService.getContentById(id);
        modelMap.addAttribute("content",content);
        return "show";
    }

    @RequestMapping(value = "/edit")
    public String editContent(HttpServletRequest request,HttpServletResponse response,
                              ModelMap modelMap,@RequestParam(value = "id",required = false) Integer id){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        if(id!=null){
            modelMap.addAttribute("content",contentService.getContentById(id));
        }
        return "edit";
    }

    @RequestMapping(value = "/editSubmit")
    public String editContentSubmit(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
                                    @RequestParam("price") double price,@RequestParam("title") String title,
                                    @RequestParam("image") String picture,@RequestParam("summary") String summary,
                                    @RequestParam("detail") String detail,@RequestParam(value = "id",required = true)int id){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        map.put("id",id);
        modelMap.addAttribute("RequestParameters",map);
        if(picture.length()>100||price>Double.MAX_VALUE){
            return "editSubmit";
        }
        Content content = contentService.getContentById(id);
        content.setPrice(price);
        content.setTitle(title);
        content.setPicture(picture);
        content.setSummary(summary);
        content.setDetail(detail);
        contentService.updateContent(content);
        content = contentService.getContentById(id);
        System.out.println("contentId:"+content.getContentId());
        modelMap.addAttribute("content",content);
        return "editSubmit";
    }

    @RequestMapping(value = "/public")
    public String publicContent(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        return "public";
    }

    @RequestMapping(value = "/publicSubmit")
    public String publicSubmit(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
                               @RequestParam("price") double price,@RequestParam("title") String title,
                               @RequestParam("image") String picture,@RequestParam("summary") String summary,
                               @RequestParam("detail") String detail){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        if(picture.length()>100 || price>Double.MAX_VALUE){
            return "publicSubmit";
        }
        Content content = new Content(price,title,picture,summary,detail);
        content.setSellerName(user.getUserName());
//        System.out.println("sellerName:"+user.getUserName());
        contentService.inserContent(content);
        content = contentService.getContent(title,picture,summary,detail,price);
        modelMap.addAttribute("content",content);

        return "publicSubmit";
    }

    @RequestMapping(value = "/api/upload")
    @ResponseBody
    public ModelMap upLoadContent(@RequestParam("file")MultipartFile file,ModelMap modelMap,
                                  HttpServletRequest request,HttpServletResponse response){
        String path = request.getSession().getServletContext().getRealPath("/uploadPicture");
        String fileName = Integer.toString(file.getOriginalFilename().hashCode())+".jpg";
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if(fileType.toLowerCase().equals("jpg")){
            String webFileNamePath = "/uploadPicture/"+fileName;
            String fileNamePath = path+"/"+fileName;
            try {
                file.transferTo(new File(fileNamePath));
            }catch (IOException e){
                e.printStackTrace();
            }
            modelMap.addAttribute("code",200);
            modelMap.addAttribute("message","success");
            modelMap.addAttribute("result",webFileNamePath);
        }else{
            modelMap.addAttribute("code",400);
            modelMap.addAttribute("message","error");
        }
        return modelMap;
    }

    @RequestMapping(value = "/api/delete")
    @ResponseBody
    public ModelMap deleteContent(@RequestParam("id") int id,ModelMap modelMap,
                                  HttpServletRequest request,HttpServletResponse response){
        User user = UserSessionUtil.getUser(request);
        if(user!=null && user.getUserType()==1){
            contentService.deleteContentById(id);
            modelMap.addAttribute("code",200);
            modelMap.addAttribute("message","success");
            modelMap.addAttribute("result",true);
        }else{
            modelMap.addAttribute("code",400);
            modelMap.addAttribute("message","抱歉，您缺乏权限进行此操作");
            modelMap.addAttribute("result",false);
        }
        return modelMap;
    }
}
