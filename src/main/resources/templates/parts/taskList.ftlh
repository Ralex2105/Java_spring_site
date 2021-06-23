<#include "security.ftlh">

<div class="card-column">
    <#list messages as message>
        <div class="card my-3">
            <div class="m-2">
                <span>${message.text}</span>
            </div>
            <div class="card-footer text-muted">
                Вид работы: <strong>${message.type}</strong>,
                Инуститут: <strong>${message.university}</strong>,
                Курс: <strong>${message.course}</strong>,
                Тег: <strong>${message.tag}</strong>,
                Автор: <strong>${message.authorName}</strong>,
                Название файла: <strong>${message.filename}</strong>

                <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                <#if message.author.id == currentUserId>
                    <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                        Edit
                    </a>
                </#if>
            </div>
        </div>
    </#list>
</div>