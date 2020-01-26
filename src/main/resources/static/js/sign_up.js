let url = "/api/users";
let form = $("#new_user");

$(function () {});

function newUser() {
    let type = "POST";
    let formData = {
        login: $("#user_email").val(),
        password: $("#user_password").val(),
        firstName: $("#user_first_name").val(),
        lastName: $("#user_last_name").val(),
        email: $("#user_email").val(),
        phone: $("#user_phone").val(),
        activated: true,
        langKey: "ru"
    };

    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(formData)
    }).done(function () {
        location.replace("/");
    });
}