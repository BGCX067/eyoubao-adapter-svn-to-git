package com.mde.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mde.entity.Category;
import com.mde.entity.Cookbook;
import com.mde.entity.Noshery;
import com.mde.model.CookbookSearcher;
import com.mde.model.Pagination;
import com.mde.model.PaginationModel;
import com.mde.model.RegisterForm;
import com.mde.service.IAdminService;
import com.mde.service.ISiteService;

@Controller("siteAction")
@Scope("prototype")
public class SiteAction extends BaseAction
{
    private static final long serialVersionUID = -1893676782240767815L;
    
    @Autowired
    private ISiteService siteService;
    
    @Autowired
    private IAdminService adminService;
    
    private RegisterForm registerForm;
    
    private Integer pageNo = 1;
    
    private CookbookSearcher searcher;
    
    public String index()
    {
        getRequest().setAttribute("npm", new PaginationModel(adminService.findNoshery(1, 10), getRequest()));
        getRequest().setAttribute("cpm", new PaginationModel(adminService.findCategory(1, 10), getRequest()));
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String forwardRegister()
    {
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String register()
    {
        siteService.register(registerForm);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String nosheryList()
    {
        Pagination<Noshery> pagination = adminService.findNoshery(pageNo, 10);
        getRequest().setAttribute("pm", new PaginationModel(pagination, getRequest()));
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String categoryList()
    {
        Pagination<Category> pagination = adminService.findCategory(pageNo, 10);
        getRequest().setAttribute("pm", new PaginationModel(pagination, getRequest()));
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String cookbookList()
    {
        Pagination<Cookbook> pagination = adminService.findCookbook(searcher, pageNo, 10);
        getRequest().setAttribute("pm", new PaginationModel(pagination, getRequest()));
        setCommonAttributes();
        return SUCCESS;
    }
    
    public RegisterForm getRegisterForm()
    {
        return registerForm;
    }
    
    public void setRegisterForm(RegisterForm registerForm)
    {
        this.registerForm = registerForm;
    }
    
    public Integer getPageNo()
    {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    
    public CookbookSearcher getSearcher()
    {
        return searcher;
    }
    
    public void setSearcher(CookbookSearcher searcher)
    {
        this.searcher = searcher;
    }
}
