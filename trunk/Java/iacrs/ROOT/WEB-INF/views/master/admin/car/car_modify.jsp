<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        $('#btn_select').click(function()
        {
            var features = 'dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=0;center=1;';
            var result = window.showModalDialog('${base}/admin/car/model_list_select.do', {}, features);
            $('#model_id').attr('value', result.id);
            $.get('${base}/admin/car/model_info.do', {
                id : result.id
            }, function(data)
            {
                if (data.id != 'undefined')
                {
                    $('#model_name').attr('value', data.name);
                    $('#dailyRental').attr('value', data.dailyRental);
                    $('#dailyPremium').attr('value', data.dailyPremium);
                    $('#preauth').attr('value', data.preauth);
                }
            });
        });
    });
</script>
</head>
<body>
    <form action="${base}/admin/car/modify_car.do" method="post">
        <input type="hidden" name="id" value="${car.car.id}"/>
        <input type="hidden" id="model_id" name="modelId" value="${car.car.modelId}"/>
        <table class="detail">
            <thead>
                <tr>
                    <td colspan="2">修改车辆</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>车型</th>
                    <td>
                        <input type="text" id="model_name" disabled="disabled" value="${car.modelAdapter.model.name}"/>
                        <input type="button" id="btn_select" value="选择" />
                    </td>
                </tr>
                <tr>
                    <th>日租金（元）</th>
                    <td>
                        <input type="text" name="dailyRental" id="dailyRental" value="${car.car.dailyRental}"/>
                    </td>
                </tr>
                <tr>
                    <th>日保险费（元）</th>
                    <td>
                        <input type="text" name="dailyPremium" id="dailyPremium" value="${car.car.dailyPremium}"/>
                    </td>
                </tr>
                <tr>
                    <th>预授权（元）</th>
                    <td>
                        <input type="text" name="preauth" id="preauth" value="${car.car.preauth}"/>
                    </td>
                </tr>
                <tr>
                    <th>车牌号</th>
                    <td>
                        <input type="text" name="licencePlate" value="${car.car.licencePlate}"/>
                    </td>
                </tr>
                <tr>
                    <th>GPS标识</th>
                    <td>
                        <input type="text" name="gpsid" value="${car.car.gpsid}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="actions">
                        <input type="submit" value="确定" />
                        <input type="button" value="返回" onclick="history.go(-1);" />
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>