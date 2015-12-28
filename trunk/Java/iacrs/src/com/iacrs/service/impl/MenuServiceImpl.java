package com.iacrs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.iacrs.context.Context;
import com.iacrs.model.MenuData;
import com.iacrs.model.MenuTreeNode;
import com.iacrs.model.TreeNode;
import com.iacrs.security.ISecurityService;
import com.iacrs.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService
{
    private static Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);
    
    @Autowired
    private Context context;
    
    @Autowired
    private ISecurityService securityService;
    
    @Override
    public List<MenuTreeNode> getRootMenus(Authentication auth)
    {
        TreeNode<MenuData> node = context.getMenuTree().getRoot();
        
        if (null == node)
        {
            return Collections.emptyList();
        }
        
        List<TreeNode<MenuData>> subnodes = node.getChildren();
        List<MenuTreeNode> visibleSubnodes = new ArrayList<MenuTreeNode>();
        
        for (TreeNode<MenuData> n : subnodes)
        {
            // 顶级节点
            if (n.getData().getForwardURI() == null)
            {
                MenuTreeNode clone = getVisibleChildrenClone((MenuTreeNode)n, auth);
                
                if (!clone.getChildren().isEmpty())
                {
                    visibleSubnodes.add(clone);
                }
            }
            else
            {
                if (isVisible(n, auth))
                {
                    visibleSubnodes.add(getVisibleChildrenClone((MenuTreeNode)n, auth));
                }
                else
                {
                    log.debug("Menu filtered, code {}, url {}", n.getData().getCode(), n.getData().getForwardURI());
                }
            }
        }
        
        return visibleSubnodes;
    }
    
    private boolean isVisible(TreeNode<MenuData> node, Authentication auth)
    {
        return !securityService.isAccessDenied(node.getData().getForwardURI(), auth);
    }
    
    private MenuTreeNode getVisibleChildrenClone(MenuTreeNode node, Authentication auth)
    {
        MenuTreeNode clone = new MenuTreeNode(node.getData());
        
        for (TreeNode<MenuData> child : node.getChildren())
        {
            if (isVisible(child, auth))
            {
                clone.addChild(child);
            }
            else
            {
                log.debug("Menu filtered, code {}, url {}", child.getData().getCode(), child.getData().getForwardURI());
            }
        }
        
        return clone;
    }
}
