const urlEvents = "/api/events";

function monthName(month) {
    var arr=[
        'Января',
        'Февраля',
        'Марта',
        'Апреля',
        'Мая',
        'Июня',
        'Июля',
        'Августа',
        'Сентября',
        'Ноября',
        'Декабря',
    ];
    return arr[month+1];
}

$(function () {
    getAll();
});

function getAll() {
    $("#container").empty();
    $.get(urlEvents).done(function (events) {
        events.forEach(function (event) {
            var date = new Date(event.date);
            var dateNumber = date.getDate();
            var dateYear = date.getFullYear();
            var dateMonth = monthName(date.getMonth());
            $("#container").append(`
                    <div class="form-row">
                        <div class="form-group col-md-1">
                            <h1>${dateNumber}</h1>
                            <h5>${dateMonth}</h5>
                            <h5>${dateYear}</h5>
                        </div>
                        <div class="form-group col-md-1"></div>
                        <div class="form-group col-md-8">
                            <div class="form-row">
                                <h3><a href="/event/${event.id}">${event.name}</a></h3>
                            </div>
                            <div class="form-row">
                                ${event.shortTitle}
                            </div>
                            <div class="form-row">${event.country}, ${event.city}</div>
                        </div>
                        <div class="form-group col-md-2">
                            <a class="btn btn-outline-danger rounded-0" href="/event/${event.id}/reg" role="button">Регистрация</a>
                            <div>Открыта до 26 марта</div>
                            <div>Участие от 1000 руб</div>
                        </div>
                    </div>
                    <hr>
                `)
        });
    })
};

function getOpenByKind(eventKind) {
    $("#container").empty();
    $.get(urlEvents + "/open?eventKind=" +  eventKind).done(function (events) {
        events.forEach(function (event) {
            var date = new Date(event.date);
            var dateNumber = date.getDate();
            var dateYear = date.getFullYear();
            var dateMonth = monthName(date.getMonth());
            $("#container").append(`
                    <div class="form-row">
                        <div class="form-group col-md-1">
                            <h1>${dateNumber}</h1>
                            <h5>${dateMonth}</h5>
                            <h5>${dateYear}</h5>
                        </div>
                        <div class="form-group col-md-1"></div>
                        <div class="form-group col-md-8">
                            <div class="form-row">
                                <h3><a href="/event/${event.id}">${event.name}</a></h3>
                            </div>
                            <div class="form-row">
                                ${event.shortTitle}
                            </div>
                            <div class="form-row">${event.country}, ${event.city}</div>
                        </div>
                        <div class="form-group col-md-2">
                            <a class="btn btn-outline-danger rounded-0" href="/event/${event.id}/reg" role="button">Регистрация</a>
                            <div>Открыта до 26 марта</div>
                            <div>Участие от 1000 руб</div>
                        </div>
                    </div>
                    <hr>
                `)
        });
    })
};