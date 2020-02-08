const urlEvents = "/api/events";

$(function () {
    var eventId = 1000;
    getEvent(eventId);
});

function getEvent(eventId) {
    $("#container").empty();
    $.get(urlEvents+"/"+eventId).done(function (event) {
        console.info(event);
        var date = new Date(event.date);
        var dateNumber = date.getDate();
        var dateYear = date.getFullYear();
        var dateMonth = date.getMonth();

        $("#container").append(`
            <div class="form-group">
                <h3>${event.name}</h3>
            </div>
            <div class="form-group">
                <h5>${dateNumber} ${dateMonth} ${dateYear}</h5>
            </div>
            <hr>
            <div class="form-group">
                ${event.title}
            </div>
            <hr>
            <div class="form-group">
                <div>Открыта до 26 марта</div>
                <div>Стоимость 1000 руб</div>
                <a class="btn btn-outline-danger rounded-0" href="/event/${event.id}/reg" role="button" id="register">Регистрация</a>
            </div>
        `);
    })
};