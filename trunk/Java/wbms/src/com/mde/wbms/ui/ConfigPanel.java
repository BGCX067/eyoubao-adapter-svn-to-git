package com.mde.wbms.ui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mde.wbms.model.Config;
import com.mde.wbms.service.IConfigService;
import com.mde.wbms.service.impl.ConfigServiceImpl;

public class ConfigPanel extends JPanel
{
    private static final long serialVersionUID = -5268278884880481957L;
    
    private JTextField waybillPath;
    
    private JTextField workPath;
    
    private JTextField mergePath;
    
    private IConfigService coreService = new ConfigServiceImpl();
    
    public ConfigPanel()
    {
        this.initilize();
    }
    
    private void initilize()
    {
        GridBagLayout layout = new GridBagLayout();
        layout.columnWeights = new double[] {0.05, 0.9, 0.05};
        layout.rowHeights = new int[]{15, 40,40,40,1};
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel(), c);
        
        
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("运单文件目录", JLabel.LEFT), c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        waybillPath = new JTextField();
        waybillPath.setEnabled(false);
        c.weightx = 1;
        this.add(waybillPath, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.gridy = 1;
        JButton button = new JButton("Browse...");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooser = new JFileChooser("C:\\");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(getParent());
                
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    waybillPath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        this.add(button, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("临时文件目录", JLabel.LEFT), c);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        workPath = new JTextField();
        workPath.setEnabled(false);
        this.add(workPath, c);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.gridy = 2;
        button = new JButton("Browse...");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooser = new JFileChooser("C:\\");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(getParent());
                
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    workPath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        this.add(button, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("合并保存目录", JLabel.LEFT), c);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        mergePath = new JTextField();
        mergePath.setEnabled(false);
        this.add(mergePath, c);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.gridy = 3;
        button = new JButton("Browse...");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooser = new JFileChooser("C:\\");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(getParent());
                
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    mergePath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        this.add(button, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 4;
        c.weighty = 1.0;
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        button = new JButton("保存配置");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if ("".equals(waybillPath.getText()))
                {
                    JOptionPane.showMessageDialog(getParent(), "请选择运单文件目录");
                    return;
                }
                
                if ("".equals(workPath.getText()))
                {
                    JOptionPane.showMessageDialog(getParent(), "请选择临时文件目录");
                    return;
                }
                
                if ("".equals(mergePath.getText()))
                {
                    JOptionPane.showMessageDialog(getParent(), "请选择合并文件目录");
                    return;
                }
                
                Config config = new Config();
                config.setWaybillPath(waybillPath.getText());
                config.setWorkPath(workPath.getText());
                config.setMergePath(mergePath.getText());
                coreService.saveConfig(config);
                JOptionPane.showMessageDialog(getParent(), "目录设置保存成功");
            }
        });
        panel.add(button);
        this.add(panel, c);
        
        Config config = coreService.getConfig();
        
        if (null != config)
        {
            waybillPath.setText(config.getWaybillPath());
            workPath.setText(config.getWorkPath());
            mergePath.setText(config.getMergePath());
        }
    }
}
