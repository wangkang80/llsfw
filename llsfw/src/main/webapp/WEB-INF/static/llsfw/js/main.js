// 添加选项卡
function addTab(iframeId, subtitle, url) {
	$.messager.progress({
		text : '页面加载中....',
		interval : 200
	});
	if (!$('#maintabs').tabs('exists', subtitle)) {
		// iframe方式
		var content = '<iframe id="' + iframeId + '" src="' + basePath + url
				+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>';
		$('#maintabs').tabs('add', {
			title : subtitle,
			content : content,
			closable : true,
			tools : [ {
				iconCls : 'icon-mini-refresh',
				handler : function() {
					var currTab = $('#maintabs').tabs('getSelected');
					var iframe = $(currTab.panel('options').content);
					var id = iframe.attr('id');
					var src = iframe.attr('src');
					document.getElementById(id).src = src;
				}
			} ]
		});

		// 自带href的方式
		// $('#maintabs').tabs('add', {
		// title : subtitle,
		// href : url,
		// closable : true
		// });
	} else {
		$('#maintabs').tabs('select', subtitle);
	}
	$.messager.progress('close');
}

$(function() {

	// 释放内存
	$.fn.panel.defaults = $.extend({}, $.fn.panel.defaults, {
		onBeforeDestroy : function() {
			var frame = $('iframe', this);
			if (frame.length > 0) {
				try {
					frame[0].contentWindowcument.write('');
				} catch (e) {
				}
				frame[0].contentWindow.close();
				frame.remove();
			}
		}
	});
});