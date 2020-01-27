let url = "/api/registrations";

$(function () {
    getRegistrations("NEW");
});

function getRegistrations (status) {
    $("#container").empty();
    $.get(url + "?status=" +  status).done(function (registrations) {
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

function confirmRegistration(registrationId) {
    var status = "PAYED";
    $.ajax({
        url: url + "/" + registrationId + "?status=" + status,
        type: "PATCH"
    }).done(function () {
        location.replace("/profile");
    });
};

function deleteRegistration(registrationId) {
    if (confirm('Вы уверены, что хотите удалить?')) {
        $.ajax({
            url: url + "/" + registrationId,
            type: "DELETE"
        }).done(function () {
            location.reload();
        });
    };
};