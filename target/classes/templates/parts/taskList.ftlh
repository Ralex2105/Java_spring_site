<div class="card-column">
    <#list messages as message>
        <div class="card my-3">
            <div class="mt-2 mx-3">
                <h6>${message.text}</h6>
            </div>
            <div class="card-footer text-muted">
                <h6>Вид работы: <strong>${message.type}</strong>,
                    Инуститут: <strong>${message.university}</strong>,
                    Курс: <strong>${message.course}</strong>,
                    Дисциплина: <strong>${message.nameOfCourse}</strong>,
                    Тег: <strong>${message.tag}</strong>,
                    Автор: <strong>${message.authorName}</strong>,
                    Название файла: <strong>${message.filenameForUser}</strong>
                </h6>
                <div class="form-inline float-right">
                    <#if message.author.id == currentUserId || isAdmin>
                        <form action="/main/${message.id}/delete" method="get">
                            <button class="btn btn-danger mx-3" type="submit">Удалить</button>
                        </form>
                        <form action="/main/${message.id}/edit" method="get">
                            <button class="btn btn-warning" type="submit">Редактировать</button>
                        </form>
                    </#if>
                    <form action="/main/${message.id}/download" method="get">
                        <button class="btn btn-info mx-3" type="submit">Посмотреть</button>
                    </form>
                    <form action="/main/${message.id}/download" method="get">
                        <button class="btn btn-primary" type="submit">Загрузить</button>
                    </form>
                </div>
            </div>
        </div>
    </#list>
</div>