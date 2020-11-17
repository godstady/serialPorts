package com.dataql.godstady.controller;

import com.dataql.godstady.mapper.ZzwInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by godstady on 2020/11/17.
 */
@Controller
public class IndexController
{
    @Autowired
    private ZzwInfoMapper mapper;

    @RequestMapping("/ls")

    public String lsindex(){
        return "zng/lszng";


    }

    @RequestMapping("/kf")
    public String kfindex(){
        return "zng/kfzng";

    }
    @RequestMapping("/index")
    public String index(){
        return "index";

    }

    @RequestMapping("/lsdata")
    @ResponseBody
    public List lsdata(){

        List<Map> lsdata = mapper.lsdata();

        return lsdata;

    }

    @RequestMapping("/kfdata")
    @ResponseBody
    public List kfdata(){

        List<Map> kfdata = mapper.kfdata();

        return kfdata;

    }


}
