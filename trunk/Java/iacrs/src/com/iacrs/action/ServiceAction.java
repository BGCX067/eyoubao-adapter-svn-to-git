package com.iacrs.action;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iacrs.exception.ServiceException;
import com.iacrs.model.ChargeForm;
import com.iacrs.service.ICarService;
import com.iacrs.service.IChargeService;
import com.iacrs.service.IRentalService;
import com.iacrs.util.ActionUtil;
import com.iacrs.util.ResponseUtils;

@Controller
@RequestMapping("/service")
public class ServiceAction
{
    @Autowired
    private IChargeService chargeService;
    
    @Autowired
    private IRentalService rentalService;
    
    @Autowired
    private ICarService carService;
    
    /**
     * 模拟充值操作回调处理,一般由第三方支付平台回调。
     */
    @RequestMapping("/charge_callback.do")
    public void chargeCallback(ChargeForm data, HttpServletResponse response)
    {
        chargeService.charge(data);
        ResponseUtils.renderJson(response, "true");
    }
    
    @RequestMapping("/read_card.do")
    public void readCard(String idcardno, String gpsid, HttpServletResponse response)
    {
        try
        {
            int result = rentalService.readCard(idcardno, gpsid);
            String key =
                IRentalService.READ_CARD_START_RENTAL == result ? "System.Rental.Start.Success"
                    : "System.Rental.End.Success";
            ResponseUtils.renderText(response, ActionUtil.getMessage(key));
        }
        catch (ServiceException e)
        {
            ResponseUtils.renderText(response, ActionUtil.getMessage(e.getCode()));
        }
    }
    
    @RequestMapping("/position_feedback.do")
    public void positionFeedback(String gpsid, double longitude, double latitude, HttpServletResponse response)
    {
        carService.setCarPosition(gpsid, longitude, latitude);
    }
}
