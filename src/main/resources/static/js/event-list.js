const urlEvents = "/api/events";

$(function () {
    getEvents();
});

function getEvents() {
    $("#container").empty();
    $.get(urlEvents).done(function (events) {
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
                            <button type="button" class="btn btn-primary btn-block" href="#" onclick="getEvent(${event.id})">Редактировать</button>
                            <button type="button" class="btn btn-secondary btn-block" href="#" onclick="deleteEvent(${event.id})">Удалить</button>
                        </div>
                    </div>
                    <hr>
                `)
        });
    })
};

function newEvent(){
    $("#modal-title").text("Новое событие");
    $("#inputId").val("");
    $("#inputName").val("");
    $("#inputKind").val("");
    $("#inputDate").val("");
    $("#inputRegStart").val("");
    $("#inputRegEnd").val("");
    $("#inputCountry").val("");
    $("#inputCity").val("");
    $("#inputShortTitle").val("");
    $("#inputTitle").val("");

    $("#inputDstLength").val("");
    $("#inputDstLimit").val("");
    $("#inputDstCost").val("");

    $('#myModal').modal();
}

function getEvent(id) {
    $("#modal-title").text("Редактировать событие");
    $.get(urlEvents + "/" + id,
        function (data) {
            var date = new Date(data.date).toISOString().substring(0, 10);
            var regStart = new Date(data.regStart).toISOString().substring(0, 10);
            var regEnd = new Date(data.regEnd).toISOString().substring(0, 10);
            $("#inputId").val(data.id);
            $("#inputName").val(data.name);
            $("#inputKind").val(data.kind);
            $("#inputDate").val(date);
            $("#inputRegStart").val(regStart);
            $("#inputRegEnd").val(regEnd);
            $("#inputCountry").val(data.country);
            $("#inputCity").val(data.city);
            $("#inputShortTitle").val(data.shortTitle);
            $("#inputTitle").val(data.title);

            $.each(data.distances, function (distance) {
                $("#inputDstLength").val(distance.length);
                $("#inputDstLimit").val(distance.participant_limit);
                $("#inputDstCost").val(0);
            });
        }).done(function () {
            $('#myModal').modal('show');
    });
}

function saveEvent() {
    let type = "POST";
    let formData = {
        id: $("#inputId").val(),
        kind: $("#inputKind").val(),
        name: $("#inputName").val(),
        date: $("#inputDate").val(),
        regStart: $("#inputRegStart").val(),
        regEnd: $("#inputRegEnd").val(),
        title: $("#inputTitle").val(),
        shortTitle: $("#inputShortTitle").val(),
        country: $("#inputCountry").val(),
        city: $("#inputCity").val(),
        distances: [
            {
                length: $("#inputDstLength").val(),
                participant_limit: $("#inputDstLimit").val()
                // $("#inputDstCost").val()
            }
        ]
    };

    var url = urlEvents;
    if (formData.id !== "") {
        url += "/" + formData.id;
        type = "PUT";
    }

    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(formData)
    }).done(function () {
        $('#myModal').modal('hide');
        location.reload();
    });
}

function deleteEvent(id) {

}