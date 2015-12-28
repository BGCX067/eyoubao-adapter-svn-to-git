package com.mde.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TreeNode<T> implements Comparable<TreeNode<T>>
{
    private T data;
    
    private TreeNode<T> parent;
    
    private List<TreeNode<T>> children = new ArrayList<TreeNode<T>>();
    
    private Map<String, TreeNode<T>> map = new HashMap<String, TreeNode<T>>();
    
    public TreeNode(T data)
    {
        this.data = data;
    }
    
    public void addChild(TreeNode<T> child)
    {
        child.parent = this;
        this.children.add(child);
        Collections.sort(this.children);
        this.map.put(child.getCascadeCode(), child);
    }
    
    public TreeNode<T> selectNode(String key)
    {
        TreeNode<T> node = map.get(key);
        
        if (null == node)
        {
            for (TreeNode<T> child : children)
            {
                node = child.selectNode(key);
                
                if (node != null)
                {
                    break;
                }
            }
        }
        
        return node;
    }
    
    public void destroy()
    {
        for (TreeNode<T> child : children)
        {
            child.destroy();
        }
        
        this.children.clear();
        this.map.clear();
    }
    
    @Override
    public int compareTo(TreeNode<T> compared)
    {
        return this.getSortNO() - compared.getSortNO();
    }
    
    public T getData()
    {
        return data;
    }
    
    public TreeNode<T> getParent()
    {
        return parent;
    }
    
    public List<TreeNode<T>> getChildren()
    {
        return children;
    }
    
    public abstract String getCascadeCode();
    
    public abstract String getCascadeParentCode();
    
    protected abstract int getSortNO();
    
}
