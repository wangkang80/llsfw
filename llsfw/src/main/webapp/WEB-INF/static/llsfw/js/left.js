/**
 * 
 */
$(function() {

	// 菜单面板
	$('#menu_tree_accordion_panel').panel({
		fit : true
	});

	// 应用列表
	$('#app_comboBox').combobox(
			{
				url : basePath + 'mainController/loadAppData',
				valueField : 'FUNCTION_CODE',
				textField : 'FUNCTION_NAME',
				editable : false,
				onLoadSuccess : function() { // 加载完成后,设置选中第一项
					var val = $(this).combobox('getData');
					for ( var item in val[0]) {
						if (item == 'FUNCTION_CODE') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect : function(param) {
					// $('#pp').panel('open').panel('refresh',
					// 'new_content.php');
					$('#menu_tree_accordion_panel').panel('refresh',
							basePath + 'mainController/getMenuTreeAccordion?appCode=' + param.FUNCTION_CODE);
				}
			});

});