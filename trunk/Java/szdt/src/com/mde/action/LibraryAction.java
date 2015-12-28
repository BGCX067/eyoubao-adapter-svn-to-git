package com.mde.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibraryAction extends BaseAction
{
    @RequestMapping("/book_list.do")
    public String bookList()
    {
        return "tushulist";
    }
    
    @RequestMapping("borrow_list.do")
    public String borrowList()
    {
        return "jieshu";
    }
}
