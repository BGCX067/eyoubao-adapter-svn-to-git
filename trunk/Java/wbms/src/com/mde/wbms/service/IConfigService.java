package com.mde.wbms.service;

import com.mde.wbms.model.Config;

public interface IConfigService
{
    Config getConfig();
    
    void saveConfig(Config config);
}
