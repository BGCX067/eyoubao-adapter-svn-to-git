package com.mde.model;

public class MenuTreeNode extends TreeNode<MenuData>
{
    public MenuTreeNode(MenuData data)
    {
        super(data);
    }
    
    @Override
    public String getCascadeCode()
    {
        return getData().getCode();
    }
    
    @Override
    public String getCascadeParentCode()
    {
        return getData().getParentCode();
    }
    
    @Override
    protected int getSortNO()
    {
        return getData().getSortNO();
    }
}
