<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Web-tasks</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная страница</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Работы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Добавление работы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Мой профиль</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользоватлей</a>
                </li>
            </#if>
        </ul>
    </div>
    <div class="navbar-text navbar-right">${name}</div>
    <@l.logout />
</nav>