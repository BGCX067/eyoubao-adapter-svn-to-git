<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>会员注册-${system_name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${base}/css/global.css" type="text/css" rel="Stylesheet" />
<script src="${thirdparty}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function()
    {
        $('#btn_goback').click(function(){
            window.location = '${base}/forward_login.do';
        });
    });
</script>
</head>
<body>
    <form action="${base}/register.do" method="post">
        <table class="detail" style="width:30%;">
            <thead>
                <tr>
                    <td colspan="2">会员注册</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>用户名</th>
                    <td>
                        <input type="text" name="username"/>
                    </td>
                </tr>
                <tr>
                    <th>密码</th>
                    <td>
                        <input type="password" name="password"/>
                    </td>
                </tr>
                <tr>
                    <th>确认密码</th>
                    <td>
                        <input type="password" id="c_password"/>
                    </td>
                </tr>
                <tr>
                    <th>姓名</th>
                    <td>
                        <input type="text" name="name"/>
                    </td>
                </tr>
                <tr>
                    <th>身份证号</th>
                    <td>
                        <input type="text" name="idcardno"/>
                    </td>
                </tr>
                <tr>
                    <th>联系电话</th>
                    <td>
                        <input type="text" name="phone"/>
                    </td>
                </tr>
                <tr>
                    <th>电子邮箱</th>
                    <td>
                        <input type="text" name="email"/>
                    </td>
                </tr>
                <tr>
                    <th>联系地址</th>
                    <td>
                        <input type="text" name="address"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="actions">
                        <input type="submit" value="注册">
                        <input type="button" id="btn_goback" value="返回">
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>