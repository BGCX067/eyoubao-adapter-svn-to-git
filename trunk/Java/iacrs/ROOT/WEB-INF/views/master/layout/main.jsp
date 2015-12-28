<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${system_name}</title>
</head>
<frameset id="mainFrameSet" cols="180,9,*" frameborder="no" border="0" framespacing="0">
    <frame src="${base}/master/left.do" name="leftFrame" noresize="noresize" id="leftFrame"/>
    <frame name=middleframe src='${base}/html/frame_split.html' scrolling='NO' frameborder="0" noresize="noresize"/>
    <frame src="${base}/master/preferred_menu.do" name="rightFrame" id="rightFrame" />
</frameset>
<noframes><body></body></noframes>
</html>