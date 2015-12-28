<!--#include file="inc/template.asp"-->
<%
dim objTemplate
set objTemplate = new template_cls
with objTemplate
    .TemplateFile = "/templates/simple/header.html"
    .AddToken "content", 1, "this is the header"
    .parseTemplateFile
end with
set objTemplate = nothing
%>