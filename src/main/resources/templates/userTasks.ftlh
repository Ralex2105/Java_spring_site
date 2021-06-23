<#import "parts/common.ftlh" as c>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/taskEdit.ftlh" />
    </#if>
    <#include "parts/taskList.ftlh" />
</@c.page>