jQuery.extend(jQuery.validator.messages, {
    required: "请输入必填项",
	remote: "已存在，请修改",
	email: "请输入正确的邮箱",
	url: "请输入正确的网址",
	date: "请输入正确的日期",
	dateISO: "请输入正确日期",
	number: "请输入数字",
	digits: "请输入整数",
	creditcard: "请输入正确的卡号",
	equalTo: "信息不一致，请再次输入",
	accept: "请输入匹配的值(后缀名)",
	maxlength: jQuery.validator.format("长度最大是 {0} 字符"),
	minlength: jQuery.validator.format("长度最小是 {0} 字符"),
	rangelength: jQuery.validator.format("请输入长度介于 {0} 和 {1} 之间的值"),
	range: jQuery.validator.format("请输入介于 {0} 和 {1} 之间的值"),
	max: jQuery.validator.format("输入值不能超过 {0}"),
	min: jQuery.validator.format("输入值不能小于 {0}")
});