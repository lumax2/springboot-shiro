package com.yoozoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Hao on 2018/3/25.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/toList")
    public String toList(){
        return "product/list";
    }

    @RequestMapping("/toAdd")
    public String add(){
        return "product/add";
    }

    @RequestMapping("/toUpdate")
    public String update(){
        return "product/update";
    }

}
