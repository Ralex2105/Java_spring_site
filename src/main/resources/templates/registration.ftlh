<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <#if message?has_content>
        <div class="alert alert-primary" role="alert">
            ${message!}
        </div>
    </#if>
    <@l.login "/registration" true />
</@c.page>