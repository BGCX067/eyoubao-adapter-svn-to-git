package com.mde.model;

public class Tree<T>
{
    private TreeNode<T> root;
    
    public Tree(TreeNode<T> root)
    {
        if (null == root)
        {
            throw new IllegalArgumentException("Root of the tree cannot be null.");
        }
        
        this.root = root;
    }
    
    public TreeNode<T> getRoot()
    {
        return root;
    }
    
    public TreeNode<T> selectNode(String key)
    {
        return root.selectNode(key);
    }
    
    public void destroy()
    {
        root.destroy();
    }
}
