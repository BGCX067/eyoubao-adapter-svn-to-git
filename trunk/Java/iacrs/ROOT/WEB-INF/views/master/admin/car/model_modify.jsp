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
    function callback(imagePath)
    {
        if (imagePath != undefined && imagePath != '')
        {
            alert('上传成功');
            var file = $('#upload_file');
            file.after(file.clone().val(""));
            file.remove();
            $('#imagePath').attr('value', imagePath);
            var img = $("#model_image");
            img.after(img.clone().attr('src', '${base}/' + imagePath));
            img.remove();
            $('#image_tr').show();
        } else
        {
            alert('上传失败');
        }
    }

    $(function()
    {
        <c:if test="${car.model.imagePath != ''}">
        $('#image_tr').show();
        </c:if>
        
        $('#btn_upload').click(function()
        {
            var file = $('#upload_file');
            if (file.val() == '')
            {
                alert('请选择需要上传的图片');
                return false;
            }

            $('#upload_form').empty();
            $('#upload_form').append(file);
            var clone = file.clone();
            clone.attr('value', '');
            $('#file_container').append(clone);
            file.attr('id', '');
            $('#upload_form').submit();
        });
    });
</script>
</head>
<body>
    <form action="${base}/admin/car/modify_model.do" method="post">
        <input type="hidden" name="id" value="${car.model.id}"/>
        <table class="detail">
            <thead>
                <tr>
                    <td colspan="2">修改车型</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>名称</th>
                    <td>
                        <input type="text" name="name" value="${car.model.name}"/>
                    </td>
                </tr>
                <tr>
                    <th>车型类别</th>
                    <td>
                        <input type="text" name="category" value="${car.model.category}"/>
                    </td>
                </tr>
                <tr>
                    <th>车型品牌</th>
                    <td>
                        <input type="text" name="brand" value="${car.model.brand}"/>
                    </td>
                </tr>
                <tr>
                    <th>车型款式</th>
                    <td>
                        <input type="text" name="style" value="${car.model.style}"/>
                    </td>
                </tr>
                <tr>
                    <th>车厢类型</th>
                    <td>
                        <select name="coachType" id="coachType">
                            <option value="0">两厢</option>
                            <option value="1">三厢</option>
                            <option value="2">其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>档位类型</th>
                    <td>
                        <select name="gearType" id="gearType">
                            <option value="0">手动挡</option>
                            <option value="1">自动挡</option>
                            <option value="2">其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>排量</th>
                    <td>
                        <input type="text" name="sweptVolume" value="${car.model.sweptVolume}"/>
                    </td>
                </tr>
                <tr>
                    <th>上传图片</th>
                    <td>
                        <input type="hidden" id="imagePath" name="imagePath" />
                        <div id="file_container">
                            <input type="file" id="upload_file" name="file" style="width: 600px;" />
                        </div>
                        <input type="button" id="btn_upload" value="上传" />
                    </td>
                </tr>
                <tr id="image_tr" style="display: none;">
                    <th>车型图片：</th>
                    <td>
                        <img src="${base}/${car.model.imagePath}" alt="" id="model_image" />
                    </td>
                </tr>
                <tr>
                    <th>日租金（元）</th>
                    <td>
                        <input type="text" name="dailyRental" value="${car.price.dailyRental}"/>
                    </td>
                </tr>
                <tr>
                    <th>日保险费（元）</th>
                    <td>
                        <input type="text" name="dailyPremium" value="${car.price.dailyPremium}"/>
                    </td>
                </tr>
                <tr>
                    <th>预授权（元）</th>
                    <td>
                        <input type="text" name="preauth" value="${car.price.preauth}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="actions">
                        <input type="submit" value="确定" />
                        <input type="button" value="返回" onclick="history.go(-1);"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
    <form id="upload_form" style="display: none;" action="${base}/master/upload.do" method="post" enctype="multipart/form-data" target="uploadFrame"></form>
    <iframe style="display: none;" name="uploadFrame" id="uploadFrame"></iframe>
</body>
</html>