<div class="card-column">
    <#list tasks as task>
        <div class="card my-3">
            <div class="mt-2 mx-3">
                <h6>${task.text}</h6>
            </div>
            <div class="card-footer text-muted">
                <h6>Вид работы: <strong>${task.type}</strong>,
                    Инуститут: <strong>${task.university}</strong>,
                    Курс: <strong>${task.course}</strong>,
                    Дисциплина: <strong>${task.nameOfCourse}</strong>,
                    Тег: <strong>${task.tag}</strong>,
                    Автор: <strong>${task.authorName}</strong>,
                    Название файла: <strong>${task.filenameForUser}</strong>
                </h6>
                <div class="form-inline float-right">
                    <#if task.author.id == currentUserId || isAdmin>
                        <form action="/main/${task.id}/delete" method="get">
                            <button class="btn btn-danger mx-3" type="submit">Удалить</button>
                        </form>
                        <form action="/main/${task.id}/edit" method="get">
                            <button class="btn btn-warning" type="submit">Редактировать</button>
                        </form>
                    </#if>
                    <form action="/main/${task.id}/download" method="get">
                        <button class="btn btn-info mx-3" type="submit">Посмотреть</button>
                    </form>
                    <form action="/main/${task.id}/download" method="get">
                        <button class="btn btn-primary" type="submit">Загрузить</button>
                    </form>
                </div>
            </div>
        </div>
    </#list>
</div>