var form;

var i18n = [];

i18n['add'] = 'Добавить';

i18n['edit'] = 'Редактировать';

i18n['common.edit'] = 'Править';

i18n['common.delete'] = 'Удалить';

function makeEditable() {
    form = $('#detailsForm');
    form.submit(function () {
        save();
        return false;
    });

}





function add(){
    form.find(":input").val("");
    $('#editRow').modal('show');

}

function save() {     
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {

            form.find();


            $('#editRow').modal('hide');

            updateTable();
         //   successNoty('Saved');
        }
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
       // url: ajaxUrl,
        type: 'DELETE',
        success: function () {
            updateTable();
       //     successNoty('Deleted');
        }
    });
}

function updateRow(id, key) {
$('#editRow').modal();
    $('#modalTitle').html(i18n[key]);

    $.get(ajaxUrl+ id, function (data) {
        $.each(data, function (key, value) {
            if( key == 'info' ) {
                form.find("textarea[name='" + key + "']").val(value);
            } else
            form.find("input[name='" + key + "']").val(
                key === "localDate.year" ? value.replace('T', ' ').substr(0, 16) : value

            );
        });
        $('#editRow').modal();
    });
}


function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}



function renderEditBtn(type, row, key) {
    if (type == 'display') {
    //    return '<a class="btn btn-xs btn-warning"  onclick="updateRow(' + row.id + ',\'' + key + '\');">' + 'Редактировать' + '</a>';
   return '<a target="_blank" class="btn btn-xs btn-warning" href="/document/pp?id='+row.id+'">'+'Редактировать'+'</a>';
    }
}

function renderPrinterBtn(type, row, key) {
    if (type == 'display') {
   return '<a target="_blank" class="btn btn-xs btn-info" href="/document/pdf?id='+row.id+'">'+'Печать'+'</a>';
    }
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ');">' + 'Удалить' + '</a>';
    }
}


function renderPrintBtn(data, type, row) {
    if (type == 'display') {
    	return '<a target="_blank" class="btn btn-xs btn-warning" href="/document/pdf?id='+row.id+'">'+'Редактировать'+'</a>';
     //   return '<a target="_blank" class="btn btn-xs btn-info" href="/document/pdf?id='+row.id+'">' + 'Печать' + '</a>';
    }
}

