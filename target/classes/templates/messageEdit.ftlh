<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <h5><strong>Редактирование работы:</strong></h5>
    <head>
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
                integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
                integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
                crossorigin="anonymous"></script>
    </head>

    <form method="post" action="/main/${message}/edit" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" value="${text}" class="form-control" name="text" placeholder="Введите название работы" required/>
        </div>
        <div class="form-group">
            <input type="text" value="${university}" class="form-control" name="university"
                   placeholder="Введите название университета" required/>
        </div>
        <div class="form-group">
            <input type="number" value="${yearOfStudy}" class="form-control" min="1" max="8" name="yearOfStudy"
                   placeholder="Введите номер курса" required/>
        </div>
        <div class="form-group">
            <input type="text" value="${nameOfCourse}" class="form-control" name="nameOfCourse" placeholder="Введите название дисциплины"
                   required>
        </div>
        <div class="form-group">
            <input type="text" value="${type}" class="form-control" name="type" placeholder="Введите тип работы" required/>
        </div>
        <div class="form-group">
            <input type="text" value="${tag}" class="form-control" name="tag" placeholder="Тэг" required>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" value="${filename}" name="file" id="customFile" required/>
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Сохранить изменения</button>
        </div>
        <script>
            $('#customFile').on('change', function () {
                //get the file name
                const fileName = $(this).val();
                //replace the "Choose a file" label
                $(this).next('.custom-file-label').html(fileName);
            })
        </script>
    </form>
</@c.page>