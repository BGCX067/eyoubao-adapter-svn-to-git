package com.iacrs.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.iacrs.model.MenuTreeNode;

public interface IMenuService
{
    List<MenuTreeNode> getRootMenus(Authentication auth);
}
