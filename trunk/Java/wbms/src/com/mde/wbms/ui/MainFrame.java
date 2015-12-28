package com.mde.wbms.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainFrame extends JFrame
{
    private static final long serialVersionUID = -2685267034766297622L;
    
    public MainFrame()
    {
        this.initilize();
    }
    
    private void initilize()
    {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("工作区");
        JMenuItem item = new JMenuItem("运单处理");
        item.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                switchContent(new WorkPanel());
            }
        });
        menu.add(item);
        bar.add(menu);
        menu = new JMenu("配置区");
        item = new JMenuItem("目录设置");
        item.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                switchContent(new ConfigPanel());
            }
        });
        menu.add(item);
        bar.add(menu);
        this.setJMenuBar(bar);
    }
    
    public void switchContent(JPanel content)
    {
        getContentPane().removeAll();
        getContentPane().add(content);
        setVisible(true);
    }
}
