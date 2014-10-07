/**
 * 
 */

$(function() {
	$('#add_user_job_table').datagrid(
			{
				method : 'post',
				fit : true,
				rownumbers : true,
				singleSelect : true,
				pagination : false,
				toolbar : '',
				queryParams : {},
				columns : [ [
						{
							title : '操作',
							field : 'LOGIN_NAME',
							align : 'left',
							width : 40,
							formatter : function(val, row, index) {
								var id = 'checkBox_' + index;
								var checked = val ? 'checked="checked"' : '';
								return '<input id="' + id + '" type="checkbox" ' + checked
										+ ' onclick="add_user_job_table_checkbox_change(\'' + id + '\',' + index + ')" />';
							}
						}, {
							title : '岗位代码',
							field : 'JOB_CODE',
							align : 'left',
							width : 100
						}, {
							title : '岗位名称',
							field : 'JOB_NAME',
							align : 'left',
							width : 100
						}, {
							title : '所属组织',
							field : 'ORG_NAME',
							align : 'left',
							width : 100
						} ] ],
				onClickRow : function(rowIndex, rowData) {
					$('#add_user_job_function_tree').tree({
						url : basePath + 'userController/loadAddUserJobFunctionTree?jobCode=' + rowData.JOB_CODE
					});
				},
				onLoadError : function() {
					showErrorWindow('数据加载失败!');
				}
			});

	$('#add_user_job_org_tree').tree(
			{
				url : basePath + 'userController/loadAllOrgTree?userName=' + $('#add_user_job_form_userName').val(),
				checkbox : false,
				parentField : "PARENT_ORG_CODE",
				textFiled : "ORG_NAME",
				idFiled : "ORG_CODE",
				formatter : function(node) {
					var text = node.text;
					if (node.MAIN_ORG == "1") {
						text = "<strong>" + text + "</strong>";
					}
					return text;
				},
				onSelect : function(node) {
					var orgCodes = "'" + node.ORG_CODE + "'";
					if (!$('#add_user_job_org_tree').tree('isLeaf', node.target)) {
						var childrenNodes = $('#add_user_job_org_tree').tree('getChildren', node.target);
						if (childrenNodes) {
							for (var i = 0; i < childrenNodes.length; i++) {
								orgCodes += ",'" + childrenNodes[i].ORG_CODE + "'";
							}
						}
					}
					$('#add_user_job_table').datagrid(
							{
								url : basePath + 'userController/loadJobList?loginName=' + $('#add_user_job_form_userName').val()
										+ '&orgCodeList=' + orgCodes
							});

				}
			});
	$('#add_user_job_org_tree_find').searchbox({
		width : '99%',
		searcher : function(value, name) {
			var node = $('#add_user_job_org_tree').tree('find', value);
			if (node) {
				$('#add_user_job_org_tree').tree('select', node.target);
			} else {
				showErrorMsg("没有此节点");
			}
		},
		prompt : '查找组织机构节点'
	});

	// 功能菜单树
	$('#add_user_job_function_tree').tree({
		checkbox : false,
		parentField : "PARENT_FUNCTION_CODE",
		textFiled : "FUNCTION_NAME",
		idFiled : "FUNCTION_CODE"
	});
});

function add_user_job_table_checkbox_change(id, index) {
	var row = $('#add_user_job_table').datagrid('getRows')[index];
	$('#add_user_job_form_jobCode').val(row.JOB_CODE);

	if ($("#" + id).is(':checked')) {
		$('#add_user_job_form_edit').attr('action', basePath + 'userController/addUserJob');
		$('#add_user_job_form_edit').form('submit', {
			onSubmit : function() {// 提交前置事件
				var isValid = $(this).form('validate');
				if (isValid) {// 验证通过,弹出遮罩
					$.messager.progress({
						text : '保存中...',
						interval : 200
					});
				}
				return isValid;
			},
			success : function(data) {
				try {
					// 关闭遮罩
					$.messager.progress('close');

					// 解析数据
					var datas = strToJson(data);

					if (datas.returnCode == '1') {
						showInfoMsg(datas.result);
					} else {
						showErrorMsg(datas.result);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	} else {
		$.messager.progress({
			text : '保存中...',
			interval : 200
		});

		$.ajax({
			type : 'POST',
			url : basePath + 'userController/deleteUserJob?loginName=' + $('#add_user_job_form_userName').val() + '&jobCode='
					+ $('#add_user_job_form_jobCode').val(),
			success : function(data) {
				// 解析数据
				var datas = strToJson(data);
				if (datas.returnCode == '1') {
					showInfoMsg(datas.result);
				} else {
					showErrorMsg("删除失败");
				}
			}
		});

		// 关闭遮罩
		$.messager.progress('close');
	}
}