jQuery(function ($) {
    $("#btn-delete-batch").on(ace.click_event, function () {
            var idArray = new Array();
            var _checked = $('input[type=checkbox]:checked');
            _checked.each(function () {
                idArray.push($(this).attr("id"))
            });
            if (_checked.length === 0) {
                bootbox.alert("请选择至少一条数据");
                return;
            } else {
                bootbox.confirm({
                    title: "删除确认?",
                    message: "确认要删除选择的数据吗?",
                    buttons: {
                        cancel: {
                            label: '<i class="fa fa-times"></i> 取消',
                        },
                        confirm: {
                            label: '<i class="fa fa-check"></i> 确定',
                        }
                    },
                    callback: function (result) {
                        console.log(result);
                        if (result == true) {
                            $.ajax({
                                "url": "/user/deleteBatch",
                                "type": "POST",
                                "data": JSON.stringify(idArray.toString()),
                                "contentType": 'application/json',
                                "dataType": "JSON",
                                "success": function (data) {
                                    console.log(data);
                                    if (data.code== 200) {
                                        bootbox.alert("删除成功.", function () {
                                            window.location.reload();
                                            }
                                        );
                                    }else{
                                        bootbox.alert("删除失败:"+data.message, function () {
                                            }
                                        );
                                    }

                                }
                            })
                        }

                    }
                });
            }
        }
    );

    $("#btn-all").on(ace.click_event, function () {
        setVal(1);
    });
    $("#btn-not").on(ace.click_event, function () {
        setVal(0);
    })
    $("#btn-rev").on(ace.click_event, function () {
        setVal(-1);
    })
});


function setVal(iNum) {
    var aArr = $('input[type=checkbox]');
    for (var i = 0; i < aArr.length; i++) {
        if (iNum < 0) {
            aArr[i].checked = !aArr[i].checked;
        } else {
            aArr[i].checked = iNum;
        }
    }
};

