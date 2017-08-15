//截取小数
function toFloatFixed(obj)
{
	return parseFloat(obj).toFixed(2);
}

function toCapital(n)
{
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
    {
    	return "数据格式错误";
    }
    var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
    n += "00";
    var p = n.indexOf('.');
    if (p >= 0)
    {
    	n = n.substring(0, p) + n.substr(p+1, 2);
    }
	unit = unit.substr(unit.length - n.length);
    for (var i=0; i < n.length; i++)
    {
    	str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    }
    return str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
}

function dateToString_1(diff){
	var showtime = "";
	var oneSecond = 1000;
	var oneMinute = oneSecond * 60;
	var oneHour = oneMinute * 60;
	var hours = parseInt(diff / oneHour);
	diff -= hours * oneHour;
	var minutes = parseInt(diff / oneMinute);
	diff -= minutes * oneMinute;
	var seconds = parseInt(diff / oneSecond);
	if (hours > 0) showtime += hours + "时";
	if (minutes > 0) showtime += minutes + "分";
	if (seconds > 0) showtime += seconds + "秒";
	return showtime;
}
function dateToString(datetime) {
	var year = datetime.getFullYear();
	var month = datetime.getMonth()+1;
	var date = datetime.getDate(); 
	var hour = datetime.getHours(); 
	var minutes = datetime.getMinutes();
	var second = datetime.getSeconds();
	if(month<10){month = "0" + month;}
	if(date<10){date = "0" + date;}
	if(hour <10){hour = "0" + hour;}
	if(minutes <10){minutes = "0" + minutes;}
	if(second <10){second = "0" + second;}
	return year+"-"+month+"-"+date+" "+hour+":"+minutes+":"+second;
}