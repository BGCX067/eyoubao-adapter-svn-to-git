$(document).ready(
		function() {
			function getNowTime() {
				var date = new Date();
				var weeks = [ "日", "一", "二", "三", "四", "五", "六" ];
				return date.toLocaleString() + "&nbsp&nbsp" + "星期"
						+ weeks[date.getDay()];
			}
			setInterval(function() {
				$("#nowTime").html(getNowTime());
			}, 1000);

			// 图书馆借书选项卡
			$(".yuyue-box>h3>span").click(function() {
				$(this).siblings().removeClass("active");
				$(this).addClass("active");
			});
		});
