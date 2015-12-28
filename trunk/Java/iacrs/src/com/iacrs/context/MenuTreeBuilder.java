package com.iacrs.context;

import java.util.Collection;

import com.iacrs.entity.Menu;
import com.iacrs.model.MenuData;
import com.iacrs.model.MenuTreeNode;
import com.iacrs.model.Tree;
import com.iacrs.model.TreeNode;

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
