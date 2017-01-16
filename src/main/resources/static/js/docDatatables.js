var ajaxUrl = 'test/';
var datatableApi;

function updateTable() {
  $.ajax({
    type: "POST",
    url: ajaxUrl,//+ 'filter',
    data: $('.form-inline').serialize(),
    success: updateTableByData
  });
  return false;
}

function filterColumn ( i ) {
  $('#datatable').DataTable().column( i ).search(
      $('#col'+i+'_filter').val(),
      $('#col'+i+'_regex').prop('checked'),
      $('#col'+i+'_smart').prop('checked')
  ).draw();
}


$(function () {
  datatableApi = $('#datatable').DataTable({
    "paging": true,
    "info": true,
    "columns": [
        {
            "data" : "ndoc"
        },
      {
        "data": "localDate",
        "render": function (date, type, row) {
          if (type == 'display') {
            return date.substr(6,2)+"."+date.substr(4,2)+"."+date.substr(0, 4);
          }
          return date;
        }
      },

      {
        "data": "c2"
      }

      ,
      {
        "data": "c1"

      }

      ,

      {
        "data": "owner.fio"
      }

      ,

      {
        "defaultContent": "",
        "orderable": false,
        "render": function (date, type, row) { return renderEditBtn(type, row, 'edit'); }
      }
      ,

      {
          "defaultContent": "",
          "orderable": false,
          "render": function (date, type, row) { return renderPrinterBtn(type, row, 'edit'); }
        }

    ]
    ,

    language: {
      "processing": "Подождите...",
      "search": "Поиск:",
      "lengthMenu": "Показать _MENU_ записей",
      "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
      "infoEmpty": "Записи с 0 до 0 из 0 записей",
      "infoFiltered": "(отфильтровано из _MAX_ записей)",
      "infoPostFix": "",
      "loadingRecords": "Загрузка записей...",
      "zeroRecords": "Записи отсутствуют.",
      "emptyTable": "В таблице отсутствуют данные",
      "paginate": {
        "first": "Первая",
        "previous": "Предыдущая",
        "next": "Следующая",
        "last": "Последняя"
      } ,
      "aria": {
        "sortAscending": ": активировать для сортировки столбца по возрастанию",
        "sortDescending": ": активировать для сортировки столбца по убыванию"
      }
    }

    ,
    "order": [
      [
        1,
        "desc"
      ]
    ]

      ,
    "initComplete": function () {

      $('#filter').submit(function () {
        updateTable();
        return false;
      });

      var startDate = $('#startDate');
      var endDate = $('#endDate');



        $('input.column_filter').on( 'keyup click', function () {
          filterColumn( $(this).parents('tr').attr('data-column') );
        } );




      /*
     startDate.datetimepicker({
       timepicker: false,
       format: 'Y-m-d',
       lang: 'ru',
       formatDate: 'Y-m-d',
       onShow: function (ct) {
         this.setOptions({
           maxDate: endDate.val() ? endDate.val() : false
         })
       }
     });
     endDate.datetimepicker({
       timepicker: false,
       format: 'Y-m-d',
       lang: 'ru',
       formatDate: 'Y-m-d',
       onShow: function (ct) {
         this.setOptions({
           minDate: startDate.val() ? startDate.val() : false
         })
       }
     });
  */

      makeEditable();

    }
  });
});

