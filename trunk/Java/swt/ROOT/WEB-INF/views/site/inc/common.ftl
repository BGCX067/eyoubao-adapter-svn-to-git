<#macro pagination model>
<#assign pagination = model.pagination />
<#assign count = pagination.totalCount!0 />
<#assign pageNo = pagination.pageNo!1 />
<#assign totalPage = pagination.totalPage!1 />
<table class="table-pagination">
    <tr>
        <td>
            <span>页次：<label class="current-page">${pageNo}</label> / <label class="total-page">${totalPage}</label>共有<label>${count}</label>条信息</span>
            <ul>
                <li>
                    <#if model.firstEnable>
                    <a href="${model.forwardFirstURL}">第一页</a>
                    <#else>
                    <label class="disabled">第一页</label>
                    </#if>
                </li>
                <li>
                    <#if model.prevEnable>
                    <a href="${model.forwardPrevURL}">上一页</a>
                    <#else>
                    <label class="disabled">上一页</label>
                    </#if>
                </li>
                <li>
                    <#if model.nextEnable>
                    <a href="${model.forwardNextURL}">下一页</a>
                    <#else>
                    <label class="disabled">下一页</label>
                    </#if>
                </li>
                <li>
                    <#if model.lastEnable>
                    <a href="${model.forwardLastURL}">最后页</a>
                    <#else>
                    <label class="disabled">最后页</label>
                    </#if>
                </li>
            </ul>
            <span>转到<input id="page" value="${pageNo}" name="page" class="input-page"/>页<input type="button" value="跳转" id="btn_goto" class="btn-goto" /></span>
        </td>
    </tr>
</table>
<script type="text/javascript">
<!--
    $(function()
    {
        $('#btn_goto').click(function()
        {
            var page = $('#page').val();
            var regex = /^[1-9][\d]*$/;
            
            if (!regex.test(page))
            {
                page = 1;
            }

            window.location='${model.forwardPageUrl}' + page;
        });
    });
//-->
</script>
</#macro>
