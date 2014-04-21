/**
 * 
 */
$(function() {

	$('#org_form_edit').form({
		onLoadSuccess : function(data) { // 加载完毕后在格式化表单
			// 加载数据
			$('#orgName_edit').val(data.orgName);
			$('#parentOrgCode_edit').val(data.parentOrgCode);
			$('#org_sort_edit').val(data.orgSort);

			// 格式化表单------------------------------------

			// 组织名称
			$('#orgName_edit').validatebox({
				required : true,
				validType : [ "length[1,100]" ]
			});

			// 上级组织
			$('#parentOrgCode_edit').combotree({
				url : basePath + 'orgController/getOrgNodeTree',
				required : false,
				lines : true,
				editable : false
			});

			// 组织排序
			$('#org_sort_edit').numberspinner({
				required : true,
				min : 1,
				editable : false
			});

			// 保存按钮
			$('#org_save_edit_btn').linkbutton({});

			// 绑定保存按钮事件
			$('#org_save_edit_btn').click(function() {
				save();
			});
		}
	});

	// 加载表单数据
	$('#org_form_edit').form('load', basePath + 'orgController/loadOrg?orgCode=' + $('#orgCode_edit').val());

	// 保存方法
	function save() {
		// 验证
		if ($('#parentOrgCode_edit').combotree('getValue') == $('#orgCode_edit').val()) {
			showErrorMsg('不能选择自身为上级组织');
			return;
		}
		$('#org_form_edit').attr('action', basePath + 'orgController/editOrg');
		// 提交
		$('#org_form_edit').form('submit', {
			onSubmit : function() {// 提交前置事件
				var isValid = $(this).form('validate');
				if (isValid) {// 验证通过,弹出遮罩
					$.messager.progress({
						text : '保存中...',
						interval : 200
					});
				}
				return isValid; // return false will stop the form submission
			},
			success : function(data) {
				try {
					// 关闭遮罩
					$.messager.progress('close');

					// 解析数据
					var datas = strToJson(data);
					if (datas.returnCode == '1') {
						$('#org_edit_window').window('close');
						$('#org_search_btn').click();
					} else {
						showErrorMsg(datas.result);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}
});
