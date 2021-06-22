<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row my-3">
            <label for="validationDefault01" class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" id="validationDefault01" required>
            </div>
        </div>
        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" id="validationDefault01" required>
            </div>
        </div>
         <#if isRegisterForm>
        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Почта</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" id="exampleInputEmail1" required>
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>