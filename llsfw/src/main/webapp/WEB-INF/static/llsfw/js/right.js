/**
 * 
 */
$(function() {

	// 初始化panel
	$('#user_online_panel').panel({
		title : '咋先1',
		fit : true,
		border : false,
		tools : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$('#user_online_table').datagrid('load', {});
			}
		} ]
	});

	// 初始化用户在线表格
	$('#user_online_table').datagrid({
		url : basePath + 'mainController/getOnlineUserList',
		method : 'post',
		rownumbers : false,
		border : false,
		fit : true,
		singleSelect : true,
		columns : [ [ {
			title : '登录名',
			field : 'loginName',
			align : 'left',
			sortable : 'true',
			width : 70
		}, {
			title : 'IP',
			field : 'loginIp',
			align : 'left',
			sortable : 'true',
			width : 100
		}, {
			title : '登陆时间',
			field : 'loginDate',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		} ] ],
		onLoadSuccess : function(data) {
			$('#user_online_panel').panel({
				title : '(' + data.rows.length + ')人在线'
			});
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});
});