$
		.extend(
				$.fn.validatebox.defaults.rules,
				{
					yyyymmdd : {
						validator : function(value) {
							var reg = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/;
							return reg.test(value);
						},
						message : '此項必須為yyyy-mm-dd格式的日期'
					},
					yyyymmddhhmiss : {
						validator : function(value) {
							var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;
							return reg.test(value);
						},
						message : '此項必須為yyyy-mm-dd hh:mi:ss格式的日期'
					},
					minLength : { // 判断最小长度
						validator : function(value, param) {
							return value.length >= param[0];
						},
						message : '最少输入 {0} 个字符。'
					},
					length : {
						validator : function(value, param) {
							var len = $.trim(value).length;
							return len >= param[0] && len <= param[1];
						},
						message : "内容长度介于{0}和{1}之间."
					},
					phone : {// 验证电话号码
						validator : function(value) {
							return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
						},
						message : '格式不正确,请使用下面格式:020-88888888'
					},
					mobile : {// 验证手机号码
						validator : function(value) {
							return /^(13|15|18)\d{9}$/i.test(value);
						},
						message : '手机号码格式不正确(正确格式如：13450774432)'
					},
					phoneOrMobile : {// 验证手机或电话
						validator : function(value) {
							return /^(13|15|18)\d{9}$/i.test(value)
									|| /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
						},
						message : '请填入手机或电话号码,如13688888888或020-8888888'
					},
					idcard : {// 验证身份证
						validator : function(value) {
							return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
						},
						message : '身份证号码格式不正确'
					},
					floatOrInt : {// 验证是否为小数或整数
						validator : function(value) {
							return /^(\d{1,3}(,\d\d\d)*(\.\d{1,3}(,\d\d\d)*)?|\d+(\.\d+))?$/i.test(value);
						},
						message : '请输入数字，并保证格式正确'
					},
					currency : {// 验证货币
						validator : function(value) {
							return /^d{0,}(\.\d+)?$/i.test(value);
						},
						message : '货币格式不正确'
					},
					qq : {// 验证QQ,从10000开始
						validator : function(value) {
							return /^[1-9]\d{4,9}$/i.test(value);
						},
						message : 'QQ号码格式不正确(正确如：453384319)'
					},
					integer : {// 验证整数
						validator : function(value) {
							return /^[+]?[1-9]+\d*$/i.test(value);
						},
						message : '请输入整数'
					},
					chinese : {// 验证中文
						validator : function(value) {
							return (/[\u4e00-\u9fa5]/g.test(value));
						},
						message : '请输入中文'
					},
					not_chinese : {// 验证非中文
						validator : function(value) {
							return !(/[\u4e00-\u9fa5]/g.test(value));
						},
						message : '请输入字母或者数字'
					},
					english : {// 验证英语
						validator : function(value) {
							return (/[[A-Za-z]]/g.test(value));
						},
						message : '请输入英文'
					},
					unnormal : {// 验证是否包含空格和非法字符
						validator : function(value) {
							return /.+/i.test(value);
						},
						message : '输入值不能为空和包含其他非法字符'
					},
					faxno : {// 验证传真
						validator : function(value) {
							return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
						},
						message : '传真号码不正确'
					},
					zip : {// 验证邮政编码
						validator : function(value) {
							return /^[1-9]\d{5}$/i.test(value);
						},
						message : '邮政编码格式不正确'
					},
					ip : {// 验证IP地址
						validator : function(value) {
							return /d+.d+.d+.d+/i.test(value);
						},
						message : 'IP地址格式不正确'
					},
					same : {
						validator : function(value, param) {
							if ($("#" + param[0]).val() != "" && value != "") {
								return $("#" + param[0]).val() == value;
							} else {
								return true;
							}
						},
						message : '两次输入的密码不一致！'
					}
				});