jQuery(function ($) {

        $(".btn-delete").on(ace.click_event, function () {
            var id = ($(this).attr("value"));
            deleteById(id);
        });

        function deleteById(id) {
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
                    if (result == true) {
                        $.ajax({
                            "url": "/user/delete",
                            "type": "POST",
                            "data": {"id": id},
                            "dataType": "JSON",
                            "success": function (data) {
                                if (data == "ok") {
                                    bootbox.alert("删除成功", function () {
                                            window.location.reload();
                                        }
                                    );
                                } else {
                                    bootbox.alert("删除失败", function () {
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
)


