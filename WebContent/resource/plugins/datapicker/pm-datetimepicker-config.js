;(function($){
	$.fn.datetimepicker.dates['zh-CN'] = {
		days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
		daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
		months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		today: "今日",
		suffix: [],
		meridiem: []
	};
	$('.date-picker').datetimepicker({
		format: 'yyyy-mm-dd', language: 'zh-CN', autoclose: 1, todayHighlight: 1, startView: 2, minView: 2
	})
	$('.date1-picker').datetimepicker({
		format: 'yyyy-mm-dd hh:ii', language: 'zh-CN', autoclose: 1, todayHighlight: 1, startView: 1, minView: 0
	})
	$('.date2-picker').datetimepicker({
		format: 'yyyy-mm-dd hh:ii:ss', language: 'zh-CN', autoclose: 1, todayHighlight: 1, startView: 1, minView: 0
	})
	$('.date3-picker').datetimepicker({
		format: 'hh:ii', language: 'zh-CN', autoclose: 1, todayHighlight: 1, startView: 0, minView: 0
	})
}(jQuery));