/**
 * 
 */
$(function() {
	// 获取cron表达式
	$('#cron_triggers_generate_save_btn').click(function() {
		$('#cron_cronExpression_add').val("");
		$('#cron_cronExpression_add').val($('#cron').val());
		$('#cron_triggers_generate_window').window('close');
	});
});
