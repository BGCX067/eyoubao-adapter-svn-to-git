package com.iacrs.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iacrs.model.RegisterForm;
import com.iacrs.service.IRegisterService;

@Controller
public class PublicAction extends BaseAction
{
    //private static Logger log = LoggerFactory.getLogger(PublicAction.class);
    
    @Autowired
    private IRegisterService registerService;
    
    @RequestMapping("/sitemap.do")
    public String sitemap()
    {
        return "sitemap";
    }
    
    @RequestMapping("/forward_register.do")
    public String forwardRegiste()
    {
        return "register";
    }
    
    @RequestMapping("register.do")
    public String register(RegisterForm data, ModelMap model)
    {
        registerService.register(data);
        return "redirect:/forward_login.do";
    }
    
    @RequestMapping("/forward_login.do")
    public String forwardLogin()
    {
        return "login";
    }
}
