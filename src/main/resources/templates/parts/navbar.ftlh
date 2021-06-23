<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">


            <li class="nav-item">
                <a class="nav-link" href="/">Главная страница</a>
            </li>
            <#if name !="Гость">
            <li class="nav-item">
                <a class="nav-link" href="/main">Работы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Мой профиль</a>
            </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользоватлей</a>
                </li>
            </#if>
        </ul>
    </div>

    <div class="mx-auto order-0">
        <a class="navbar-brand mx-auto">Web-tasks</a>
    </div>


    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="navbar-text mx-2">${name}</a>
            </li>

            <#if name!="Гость"><@l.logout/></#if>
            <#if name="Гость"><@l.loginButton/></#if>
        </ul>
    </div>
</nav>