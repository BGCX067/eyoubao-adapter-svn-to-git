package com.mde.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.mde.model.MenuTreeNode;

public interface IMenuService
{
    List<MenuTreeNode> getRootMenus(Authentication auth);
}
