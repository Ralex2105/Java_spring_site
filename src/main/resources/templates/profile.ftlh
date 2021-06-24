<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>


<@c.page>
    <#if messages?size != 0>
        <h5><strong>Ваши работы:</strong></h5>
        <#include "parts/taskList.ftlh">
    <#else>
        <h5><strong>У вас нет работ</strong></h5>
    </#if>
</@c.page>