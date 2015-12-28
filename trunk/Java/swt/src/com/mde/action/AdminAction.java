package com.mde.action;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mde.entity.Category;
import com.mde.entity.Cookbook;
import com.mde.entity.Noshery;
import com.mde.entity.User;
import com.mde.model.Pagination;
import com.mde.model.PaginationModel;
import com.mde.service.IAdminService;
import com.mde.service.IUploadService;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends BaseAction
{
    private static final long serialVersionUID = 2088897971389206031L;
    
    @Autowired
    private IAdminService adminService;
    
    @Autowired
    private IUploadService uploadService;
    
    private Integer pageNo = 1;
    
    private Integer id;
    
    private Noshery noshery;
    
    private Category category;
    
    private Cookbook cookbook;
    
    private File file;
    
    private String fileContentType;
    
    private String fileFileName;
    
    public String userList()
    {
        Pagination<User> pagination = adminService.findUser(pageNo, 10);
        getRequest().setAttribute("pm", new PaginationModel(pagination, getRequest()));
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String deleteUser()
    {
        adminService.deleteUser(id);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String upload()
    {
        String rootPath = getRequest().getSession().getServletContext().getRealPath("/");
        String imagePath = uploadService.upload(fileFileName, file, rootPath);
        String returnText = imagePath.replaceAll("\\\\", "/");
        getRequest().setAttribute("returnText", returnText);
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
    
    public String forwardAddNoshery()
    {
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String addNoshery()
    {
        adminService.addNoshery(noshery);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String forwardModifyNoshery()
    {
        Noshery data = adminService.getNoshery(noshery.getId());
        getRequest().setAttribute("data", data);
        getRequest().setAttribute("modify", true);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String modifyNoshery()
    {
        adminService.modifyNoshery(noshery);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String deleteNoshery()
    {
        adminService.deleteNoshery(noshery.getId());
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
    
    public String forwardAddCategory()
    {
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String addCategory()
    {
        adminService.addCategory(category);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String forwardModifyCategory()
    {
        Category data = adminService.getCategory(category.getId());
        getRequest().setAttribute("data", data);
        getRequest().setAttribute("modify", true);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String modifyCategory()
    {
        adminService.modifyCategory(category);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String deleteCategory()
    {
        adminService.deleteCategory(category.getId());
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String cookbookList()
    {
        Pagination<Cookbook> pagination = adminService.findCookbook(pageNo, 10);
        getRequest().setAttribute("pm", new PaginationModel(pagination, getRequest()));
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String forwardAddCookbook()
    {
        getRequest().setAttribute("categories", adminService.findCategory());
        getRequest().setAttribute("nosheries", adminService.findNoshery());
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String addCookbook()
    {
        adminService.addCookbook(cookbook);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String forwardModifyCookbook()
    {
        Cookbook data = adminService.getCookbook(cookbook.getId());
        getRequest().setAttribute("data", data);
        getRequest().setAttribute("modify", true);
        getRequest().setAttribute("categories", adminService.findCategory());
        getRequest().setAttribute("nosheries", adminService.findNoshery());
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String modifyCookbook()
    {
        adminService.modifyCookbook(cookbook);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String deleteCookbook()
    {
        adminService.deleteCookbook(cookbook.getId());
        setCommonAttributes();
        return SUCCESS;
    }
    
    public Integer getPageNo()
    {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Noshery getNoshery()
    {
        return noshery;
    }
    
    public void setNoshery(Noshery noshery)
    {
        this.noshery = noshery;
    }
    
    public File getFile()
    {
        return file;
    }
    
    public void setFile(File file)
    {
        this.file = file;
    }
    
    public String getFileContentType()
    {
        return fileContentType;
    }
    
    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
    }
    
    public String getFileFileName()
    {
        return fileFileName;
    }
    
    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }
    
    public Category getCategory()
    {
        return category;
    }
    
    public void setCategory(Category category)
    {
        this.category = category;
    }
    
    public Cookbook getCookbook()
    {
        return cookbook;
    }
    
    public void setCookbook(Cookbook cookbook)
    {
        this.cookbook = cookbook;
    }
}
