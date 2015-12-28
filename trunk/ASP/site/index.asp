<%@LANGUAGE="VBSCRIPT" CODEPAGE="65001"%>
<!--#include file="inc/template.asp"-->
<!--#include file="inc/config.asp"-->
<%
    dim name, base, themes, parser
    name = session("site_name")
    base = session("base")
    themes = base & "/themes/" & session("themes")
    
    set parser = new template_cls
    
    '设置公共属性
    parser.AddToken "base", 1, base
    parser.AddToken "themes", 1, themes
    parser.AddToken "site_name", 1, name
    parser.AddToken "global_css", 2, themes & "/global_css.html"
    parser.AddToken "global_js", 2, themes & "/global_js.html"
    parser.AddToken "header", 2, themes & "/header.html"
    parser.AddToken "footer", 2, themes & "/footer.html"
    
    '设置页面转悠属性
    parser.TemplateFile = themes & "/index.html"
    parser.parseTemplateFile
    set parser = nothing
%>