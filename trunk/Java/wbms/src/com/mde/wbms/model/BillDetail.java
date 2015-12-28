package com.mde.wbms.model;

import com.mde.wbms.ui.Resolvable;

public class BillDetail 
{
    @Resolvable(name="采购单号",sort = 1)
    private String purchaseNo;
    
    @Resolvable(name="发票号",sort = 2)
    private String invoiceNo;
    
    @Resolvable(name="运单号",sort = 3)
    private String waybillNo;
    
    @Resolvable(name="重量",sort = 4)
    private String weight;
    
    public String getPurchaseNo() 
    {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) 
    {
        this.purchaseNo = purchaseNo;
    }

    public String getInvoiceNo() 
    {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) 
    {
        this.invoiceNo = invoiceNo;
    }

    public String getWaybillNo() 
    {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) 
    {
        this.waybillNo = waybillNo;
    }

    public String getWeight() 
    {
        return weight;
    }

    public void setWeight(String weight) 
    {
        this.weight = weight;
    }
}
