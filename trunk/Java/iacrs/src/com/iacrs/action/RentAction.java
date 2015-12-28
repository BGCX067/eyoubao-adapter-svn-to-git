package com.iacrs.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iacrs.model.Pagination;
import com.iacrs.model.PaginationModel;
import com.iacrs.model.RentBillAdapter;
import com.iacrs.service.IRentalService;

@Controller
@RequestMapping("/admin/rent")
public class RentAction extends BaseAction
{
    @Autowired
    private IRentalService rentalService;
    
    @RequestMapping("/rent_list.do")
    public String rentList(HttpServletRequest request, ModelMap model, @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo)
    {
        Pagination<RentBillAdapter> pagination = rentalService.find(pageNo, 20);
        model.addAttribute(PaginationModel.MODEL_KEY, new PaginationModel(pagination, request));
        return "master/admin/rent/bill_list";
    }
}
