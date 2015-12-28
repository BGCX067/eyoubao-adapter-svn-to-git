package com.mde.service.impl;

import java.util.Collection;

import com.mde.entity.Menu;
import com.mde.model.MenuData;
import com.mde.model.MenuTreeNode;
import com.mde.model.Tree;
import com.mde.model.TreeNode;

public class MenuTreeBuilder extends TreeBuilder<Menu, MenuData>
{
    public MenuTreeBuilder(Collection<Menu> records)
    {
        super(records);
    }
    
    @Override
    protected TreeNode<MenuData> generateRoot()
    {
        TreeNode<MenuData> root = new MenuTreeNode(new MenuData());
        root.getData().setCode("0");
        return root;
    }
    
    @Override
    protected TreeNode<MenuData> generateNode(Menu record)
    {
        return new MenuTreeNode(new MenuData(record.getCode(), record.getParentCode(), record.getName(),
            record.getForwardURI(), record.getSortNO()));
    }
    
    @Override
    protected Tree<MenuData> generateTree(TreeNode<MenuData> root)
    {
        return new Tree<MenuData>(root);
    }
}
