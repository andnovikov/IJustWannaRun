let urlRegistration = "/api/registrations";
let urlUser = "/api/user";
let urlUsers = "/api/users";

$(function () {
    getRegistrations("PAYED");
});

function getRegistrations () {
    $("#container").empty();
    var status = "PAYED";
    $.get(urlRegistration + "?status=" +  status).done(function (registrations) {
        registrations.forEach(function (registration) {
            var date = new Date(registration.event.date);
            console.debug(date);
            $("#container").append(`
                    <div class="form-row">
                        <div class="form-group col-md-2">${date.toLocaleDateString()}</div>
                        <div class="form-group col-md-6">
                            <div class="form-row">
                                <h4><a href="/event/${registration.event.id}">${registration.event.name}</a></h4>
                            </div>
                            <div class="form-row">${registration.event.city}</div>
                        </div>
                        <div class="form-group col-md-2">
                            <h5>Номер: ${registration.regNumber}</h5>
                        </div>
                    </div>
                    <hr>
                `)
        });
    })
};

function getOrders () {
    $("#container").empty();
    var status = "NEW";
    $.get(urlRegistration + "?status=" +  status).done(function (registrations) {
        registrations.forEach(function (registration) {
            var date = new Date(registration.event.date);
            console.debug(date);
            $("#container").append(`
                    <div class="form-row">
                        <div class="form-group col-md-2">${date.toLocaleDateString()}</div>
                        <div class="form-group col-md-6">
                            <div class="form-row">
                                <h4><a href="/event/${registration.event.id}">${registration.event.name}</a></h4>
                            </div>
                            <div class="form-row">${registration.event.city}</div>
                        </div>
                        <div class="form-group col-md-2">
                            <a class="btn btn-primary rounded-0" href="javascript:confirmRegistration('${registration.id}');" role="button">Оплатить</a>
                        </div>
                        <div class="form-group col-md-2">
                            <a class="btn btn-danger rounded-0" href="javascript:deleteRegistration('${registration.id}');" role="button">Удалить</a>
                        </div>
                    </div>
                    <hr>
                `)
        });
    })
};

function getUserData (status) {
    $("#container").empty();
    $.get(urlUser).done(function (user) {
        var birthday = new Date(user.birthday).toISOString().substring(0, 10);
        $("#container").append(`
            <form id="new_user" action="/" accept-charset="UTF-8" method="post">
                <div class="row">
                    <div class="col-md-12 mx-auto">
                        <input class="form-control" type="text" name="user_id" id="user_id" value="${user.id}" hidden="true">
                        <div class="form-group row field">
                            <label class="col-sm-3 col-form-label" for="user_email">Email</label>
                            <div class="col-sm-4">
                                <input autofocus="autofocus" autocomplete="email" class="form-control" type="email" value="${user.email}" name="user_email" id="user_email" disabled>
                            </div>
                        </div>
                        <div class="form-group row field">
                            <label class="col-sm-3 col-form-label" for="user_first_name">Имя</label>
                            <div class="col-sm-4">
                                <input class="form-control" type="text" name="user_first_name" id="user_first_name" value="${user.firstName}">
                            </div>
                        </div>
                        <div class="form-group row field">
                            <label class="col-sm-3 col-form-label" for="user_last_name">Фамилия</label>
                            <div class="col-sm-4">
                                <input class="form-control" type="text" name="user_last_name" id="user_last_name" value="${user.lastName}">
                            </div>
                        </div>
                        <div class="form-group row field">
                            <label class="col-sm-3 col-form-label" for="user_birthday">Дата Рождения</label>
                            <div class="col-sm-4">
                                <input class="form-control" type="date" name="user_birthday" id="user_birthday" value="${birthday}"></div>
                            </div>
                        <div class="form-group row field">
                            <label class="col-sm-3 col-form-label" for="user_phone">Телефон</label>
                            <div class="col-sm-4">
                                <input type="phone" class="form-control" name="user_phone" id="user_phone" placeholder="+79999999" value="${user.phone}">
                            </div>
                        </div>
                        <div class="row pt-3">
                            <div class="col-sm-7">
                                <div class="actions">
                                    <button type="button" class="btn btn-sm btn-primary" onclick="updateUserProfile()">Сохранить</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>  
        `)
    });
};

function confirmRegistration(registrationId) {
    var status = "PAYED";
    $.ajax({
        url: urlRegistration + "/" + registrationId + "?status=" + status,
        type: "PATCH"
    }).done(function () {
        location.replace("/profile");
    });
};

function deleteRegistration(registrationId) {
    if (confirm('Вы уверены, что хотите удалить?')) {
        $.ajax({
            url: urlRegistration + "/" + registrationId,
            type: "DELETE"
        }).done(function () {
            location.reload();
        });
    };
};

function updateUserProfile() {
    let type = "PUT";
    let formData = {
        id: $("#user_id").val(),
        login: $("#user_email").val(),
        firstName: $("#user_first_name").val(),
        lastName: $("#user_last_name").val(),
        birthday: $("#user_birthday").val(),
        email: $("#user_email").val(),
        phone: $("#user_phone").val(),
        activated: true,
        langKey: "ru"
    };

    $.ajax({
        url: urlUsers + "/" + $("#user_id").val(),
        type: type,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(formData)
    }).done(function () {
        getRegistrations("PAYED");
    });
}