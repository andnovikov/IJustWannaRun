$(function () {
    getAll();
});

function getAll () {
    $.get('/api/event').done(function (events) {
        events.forEach(function (event) {
            var date = new Date(event.date);
            console.debug(date);
            var dateNumber = date.getDate();
            console.debug(dateNumber);
            var dateYear = date.getFullYear();
            $("#container").append(`
                    <div class="form-row">
                        <div class="form-group col-md-1">
                            <h1>${dateNumber}</h1>
                            <h5>апреля</h5>
                            <h5>${dateYear}</h5>
                            <h5>БЕГ</h5>
                            ${event.date}
                        </div>
                        <div class="form-group col-md-1"></div>
                        <div class="form-group col-md-8">
                            <div class="form-row">
                                <h3><a href="/event/${event.id}">${event.name}</a></h3>
                            </div>
                            <div class="form-row">
                                ${event.shortTitle}
                            </div>
                            <div class="form-row">Москва</div>
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