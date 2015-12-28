package com.iacrs.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iacrs.model.Tree;
import com.iacrs.model.TreeNode;

public abstract class TreeBuilder<E, T>
{
    private Collection<E> records;
    
    public TreeBuilder(Collection<E> records)
    {
        this.records = records;
    }
    
    protected abstract TreeNode<T> generateRoot();
    
    protected abstract TreeNode<T> generateNode(E record);
    
    protected abstract Tree<T> generateTree(TreeNode<T> root);
    
    public Tree<T> build()
    {
        TreeNode<T> root = generateRoot();
        TreeNode<T> node;
        TreeNode<T> parentNode;
        Map<String, TreeNode<T>> allNodeMap = new HashMap<String, TreeNode<T>>();
        allNodeMap.put(root.getCascadeCode(), root);
        List<TreeNode<T>> unboundList = new ArrayList<TreeNode<T>>();
        
        for (E record : records)
        {
            node = generateNode(record);
            allNodeMap.put(node.getCascadeCode(), node);
            parentNode = allNodeMap.get(node.getCascadeParentCode());
            
            // 第一次循环时，可能父节点记录还没有遍历到，所以先放入列表等待2次遍历
            if (null == parentNode)
            {
                unboundList.add(node);
            }
            else
            {
                parentNode.addChild(node);
            }
        }
        
        for (TreeNode<T> unboundNode : unboundList)
        {
            parentNode = allNodeMap.get(unboundNode.getCascadeParentCode());
            
            // 第二次遍历时还找不到父节点记录，说明数据库中配置的记录有问题，直接抛出异常定位数据库中的记录
            if (null == parentNode)
            {
                throw new IllegalStateException("invalid tree node data in database, code:"
                    + unboundNode.getCascadeCode());
            }
            
            parentNode.addChild(unboundNode);
        }
        
        return generateTree(root);
    }
}
