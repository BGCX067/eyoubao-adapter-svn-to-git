<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c-rt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${base}/css/global.css" type="text/css" rel="Stylesheet" />
<title>${system_name}</title>
</head>
<body class="left-body">
    <div class="left-content">
        <ul class="left-menu">
            <c:forEach var="menu" items="${menus}">
                <li>
                    <label>${menu.data.name}</label>
                    <ul>
                        <c:forEach var="submenu" items="${menu.children}">
                        <li><a href="${base}${submenu.data.forwardURI}" target="rightFrame">${submenu.data.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>