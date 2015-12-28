<#macro booking_table data date_interval>
<table class="table">
    <thead>
        <tr>
            <th class="first">编号</th>
            <#list data.intervals as interval>
            <th>${interval.start!?html}-${interval.end!?html}</th>
            </#list>
        </tr>
    </thead>
    <tbody>
        <#list data.items as item>
        <tr>
            <th class="first">${item.name!?html}</th>
            <#list data.intervals as interval>
            <#assign entryKey = (interval.id + "-" + item.id) />
            <#assign entry = data.entries[entryKey]/>
            <#assign prompt = entry.records?size + "/"+ item.count>
            <#if entry.bookingable>
            <td id="${interval.id}-${item.id}-${date_interval}">可预约<#if 1 < item.count>${prompt}</#if></td>
            <#else>
            <td class="active" id="${interval.id}-${item.id}-${date_interval}">已预约<#if 1 < item.count>${prompt}</#if></td>
            </#if>
            </#list>
        </tr>
        </#list>
    </tbody>
</table>
</#macro>