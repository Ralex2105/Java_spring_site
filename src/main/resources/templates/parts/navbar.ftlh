<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Web-tasks</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
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
                <a class="nav-link" href="/user">Список пользователей</a>
            </li>
            </#if>
        </ul>
        <div class="navbar-text">${name}</div>
    </div>
</nav>