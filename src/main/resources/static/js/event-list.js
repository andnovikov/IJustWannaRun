$(function () {
    getEvents();
});

function getEvents() {
    $("#container").empty();
    $.get('/api/events').done(function (events) {
        events.forEach(function (event) {
            var date = new Date(event.date).toISOString().slice(0,10);
            $("#container").append(`
                    <div class="form-row">
                        <div class="form-group col-md-1">${event.id}</div>
                        <div class="form-group col-md-1">${event.status}</div>
                        <div class="form-group col-md-1">${event.kind}</div>
                        <div class="form-group col-md-4">${event.name}</div>
                        <div class="form-group col-md-2">${date}</div>
                        <div class="form-group col-md-1">${event.country}, ${event.city}</div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-primary btn-block" onclick="">Редактировать</button>
                            <button type="button" class="btn btn-secondary btn-block" onclick="">Удалить</button>
                        </div>
                    </div>
                    <hr>
                `)
        });
    })
};

function newEvent(){
    $("#modal-title").text("Новая книга");
    $("#inputId").val("");
    $("#inputTitle").val("");

    $.get(urlAuthor,
        function (data) {
            let select = $("#author-input")
            select.empty();
            $.each(data, function (key, value) {
                select.append(
                    $("<option></option>")
                        .attr("value", value.id)
                        .text(value.first_name + " " + value.last_name));
            });
        });

    $.get(urlGenre,
        function (data) {
            let select = $("#genre-input");
            select.empty();
            $.each(data, function (key, value) {
                select.append(
                    $("<option></option>")
                        .attr("value", value.id)
                        .text(value.genre_name));
            });
        });
    $('#myModal').modal();
}