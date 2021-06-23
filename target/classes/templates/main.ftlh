<#import "parts/common.ftlh" as c>

<@c.page>
        <div class="form-group">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Поиск по названию">
                <button type="submit" class="btn btn-primary ml-2">Поиск</button>
            </form>
        </div>

<#include "parts/taskEdit.ftlh" />

<#include "parts/taskList.ftlh" />
</@c.page>