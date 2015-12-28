package com.mde.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/overtime")
public class OvertimeAction extends BaseAction
{
    @RequestMapping("/apply.do")
    public String apply()
    {
        return "shenpi";
    }
    
    @RequestMapping("/records.do")
    public String records()
    {
        return "jiaban";
    }
}
