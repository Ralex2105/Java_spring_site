<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <#if !isRegisterForm>
                                <h5 class="card-title text-center">Вход</h5>
                            </#if>

                            <#if isRegisterForm>
                                <h5 class="card-title text-center">Регистрация</h5>
                            </#if>
                            <form class="form-signin">
                                <div class="form-label-group my-3">
                                    <input type="text" name="username" id="Введите логин" class="form-control" placeholder="Логин" required>
                                </div>

                                <div class="form-label-group my-3">
                                    <input type="password" name="password" id="inputPassword" class="form-control"
                                           placeholder="Пароль" required>
                                </div>

                                <#if isRegisterForm>
                                    <div class="form-label-group my-3">
                                        <input type="email" name="email" id="Ввелите email" class="form-control"
                                               placeholder="Почтовый ящик" required>
                                    </div>
                                </#if>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase"
                                        type="submit"><#if isRegisterForm>Create<#else>Войти</#if></button>
                                <#if !isRegisterForm>
                                    <hr class="my-4">
                                    <a href="/registration" class="btn btn-lg btn-primary btn-block text-uppercase"
                                       role="button">Зарегистрироваться</a>
                                </#if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>

<#macro loginButton>
    <form action="/login" method="post">
        <button class="btn btn-primary" type="submit">Войти</button>
    </form>
</#macro>