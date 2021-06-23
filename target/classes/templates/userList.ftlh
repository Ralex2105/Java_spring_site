<#import "parts/common.ftlh" as c>
<@c.page>
    Список пользователей
    <table>
        <thead>
        <tr>
            <th>Логин</th>
            <th>Роль</th>
            <th></th>
            <th>Почта</th>
            <th>Подтверждение</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">edit</a></td>
                <td>  ${user.getEmail()}</td>
                <td>  ${user.getActivation(user)}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>