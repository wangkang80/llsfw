/**
 * 
 */
$(function() {
	// 分页条数
	var pageSize = getServerParam(basePath, 'PAGE_SIZE');

	// 构造表格
	$('#scheduler_log_table').datagrid({
		url : basePath + 'quartzController/loadSchedulerLogList',
		method : 'post',
		rownumbers : true,
		fit : true,
		singleSelect : true,
		pagination : true,
		pageSize : pageSize,
		pageList : [ pageSize ],
		columns : [ [ {
			title : '记录',
			field : 'MSG',
			align : 'left',
			width : 550
		}, {
			title : '时间',
			field : 'CREATE_DATE',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		} ] ],
		rowStyler : function(rowIndex, rowData) {
			if (rowData.MSG.indexOf('(schedulerError)') != -1) {
				return 'background-color:red;color:#fff;font-weight:bold;';
			} else if (rowData.MSG.indexOf('(schedulerShutdown)') != -1 || rowData.MSG.indexOf('(schedulerShuttingdown)') != -1) {
				return 'background-color:blue;color:#fff;font-weight:bold;';
			} else if (rowData.MSG.indexOf('(schedulerInStandbyMode)') != -1) {
				return 'background-color:yellow;color:#000;font-weight:bold;';
			}
		},
		onDblClickRow : function(rowIndex, rowData) {
			$('#scheduler_log_msg_dialog').dialog({
				title : '详细信息',
				width : 500,
				height : 500,
				cache : false,
				maximizable : true,
				content : rowData.MSG
			});
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});
});