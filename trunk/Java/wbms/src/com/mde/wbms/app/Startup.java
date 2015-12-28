package com.mde.wbms.app;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mde.wbms.model.Config;
import com.mde.wbms.service.IConfigService;
import com.mde.wbms.service.impl.ConfigServiceImpl;
import com.mde.wbms.ui.ConfigPanel;
import com.mde.wbms.ui.MainFrame;
import com.mde.wbms.ui.WorkPanel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;

public class Startup
{
    private static Logger log = LoggerFactory.getLogger(Startup.class);
    
    public static void main(String[] args)
    {
        // 设置界面风格
        setLookAndFeel();
        log.info("Set system look and feel successful.");
        IConfigService coreService = new ConfigServiceImpl();
        Config config = coreService.getConfig();
        
        MainFrame frame = new MainFrame();
        
        if (null == config)
        {
            log.info("First access the system, should init the path information.");
            frame.getContentPane().add(new ConfigPanel());
        }
        else
        {
            frame.getContentPane().add(new WorkPanel());
        }
        
        frame.setTitle("运单处理程序");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(frame.getOwner());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog((Frame)e.getSource(),
                    "确定退出该程序？",
                    "确认",
                    JOptionPane.YES_NO_OPTION))
                {
                    System.exit(0);
                }
                else
                {
                    return;
                }
            }
        });
        
        frame.setVisible(true);
    }
    
    private static void setLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e)
        {
            log.error(e.getMessage(), e);
        }
    }
}
