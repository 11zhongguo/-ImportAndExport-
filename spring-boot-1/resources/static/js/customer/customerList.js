/**
 * Created by LiFei on 2018/3/26.
 */

$(function () {
    $('#foldBtn').on('click', function () {
        var $this = $(this),
            $formGroup = $('#customerForm div.form-group:not(:last)'),
            $hideFormGroup = $('#customerForm div.form-group:gt(2):not(:last)'),
            $btnFormGroup= $('#customerForm div.form-group:last');
        if($this.hasClass('open')){
           $this.removeClass('open').text('收起条件');
           $formGroup.removeClass('hidden col-md-3 col-sm-3').addClass('col-md-3 col-sm-3');
           $btnFormGroup.removeClass('col-md-3 col-sm-3').addClass('col-md-12 col-sm-12');
        } else{
           $this.addClass('open').text('展开更多');
           $formGroup.removeClass('col-md-3 col-sm-3').addClass('col-md-3 col-sm-3');
           $hideFormGroup.addClass('hidden');
           $btnFormGroup.removeClass('col-md-12 col-sm-12').addClass('col-md-3 col-sm-3');
        }
    });


    $('#createCustomerBtn').on('click', function () {
        $('#myModal').modal('toggle');
    });
});