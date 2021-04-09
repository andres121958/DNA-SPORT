/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#example').DataTable({
        responsive: true,
        dom: 'Bfrtilp',
        paging: false,
        lengthChange: false,
        searching: false,
        ordering: true,
        info: false,
        autoWidth: false,
        buttons: [
            {
                extend: 'excelHtml5',
                text: '<i class="fas fa-file-excel"></i>',
                tittleAttr: 'Exportar a Excel',
                className: 'btn btn-success'
            },
            {
                extend: 'pdfHtml5',
                text: '<i class="fas fa-file-pdf"></i>',
                tittleAttr: 'Exportar a PDF',
                className: 'btn btn-danger'
            },
            {
                extend: 'print',
                text: '<i class="fas fa-print"></i>',
                tittleAttr: 'Imprimir',
                className: 'btn btn-info'
            }
        ]
    });
});

