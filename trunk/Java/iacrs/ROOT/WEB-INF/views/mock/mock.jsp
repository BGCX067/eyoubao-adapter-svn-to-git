<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c-rt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${system_name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${base}/css/global.css" type="text/css" rel="Stylesheet" />
<script src="${thirdparty}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function()
    {
        $('#btn_ok').click(function()
        {
            $.post($("#mock_form").attr('action'), $("#mock_form").serialize(), function(result)
            {
                alert(result);
            });
        });

        $('#btn_position_ok').click(function()
        {
            $.post($("#mock_position_form").attr('action'), $("#mock_position_form").serialize(), function(result)
            {
                alert('操作成功');
            });
        });
    });
</script>
</head>
<body>
    <form id="mock_form" action="${base}/service/read_card.do">
        <table class="detail" style="width: 50%;">
            <thead>
                <tr>
                    <td colspan="2">模拟刷卡</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>模拟GPS标识</th>
                    <td>
                        <input type="text" name="gpsid" value="0888" />
                    </td>
                </tr>
                <tr>
                    <th>模拟身份证号</th>
                    <td>
                        <input type="text" name="idcardno" style="width: 400px;" value="320902198410290000" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="actions">
                        <input type="button" id="btn_ok" value="确定">
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
    <form id="mock_position_form" action="${base}/service/position_feedback.do">
        <table class="detail" style="width: 50%;">
            <thead>
                <tr>
                    <td colspan="2">模拟定位信息传送</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>模拟GPS标识</th>
                    <td>
                        <input type="text" name="gpsid" value="0888" />
                    </td>
                </tr>
                <tr>
                    <th>经度</th>
                    <td>
                        <input type="text" name="longitude" value="116.5353"/>
                    </td>
                </tr>
                <tr>
                    <th>纬度</th>
                    <td>
                        <input type="text" name="latitude" value="39.9657"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="actions">
                        <input type="button" id="btn_position_ok" value="确定">
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>