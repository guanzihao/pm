$(function(){
		$('.fixbtn .fix-type-right a').hover(function(){
//			$(this).stop().animate({'right':'-188px'}, 300);
			$(this).addClass('on');
			$('.block-content', this).fadeIn();
		}, function(){
//			$(this).stop().animate({'left':'0px'}, 300);
			$(this).removeClass('on');
			$('.block-content', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-over a').hover(function(){
			$(this).addClass('on');
		}, function(){
			$(this).removeClass('on');
		});
		$('.fixbtn .fix-type-fade a').hover(function(){
			$('.block-fade', this).fadeIn();
		}, function(){
			$('.block-fade', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-top a').click(function(){
			$("html, body").animate({scrollTop: 0}, 600);
		}).addClass('on').parent('li').hide();
		
		$(window).scroll(function (){
			var height = document.documentElement.clientHeight;
			if(0===$(window).scrollTop())
				$('.fixbtn .fix-type-top').stop().slideUp();
			else
				$('.fixbtn .fix-type-top').slideDown();

			if($(window).scrollTop() > (2000 - height)){
				$('#fixbtn').css({'bottom':'40%'})
			}
			if($(window).scrollTop() === 0){
				$('#fixbtn').css({'bottom':'20%'});
			}
		}).trigger('scroll');

		$('.fixbtn .fix-type-top a').hover(function(){
			$('.back-top', this).fadeIn();
		}, function(){
			$('.back-top', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-over a').hover(function(){
			$('.online', this).fadeIn();
		}, function(){
			$('.online', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-over a').hover(function(){
			$('.send-mail', this).fadeIn();
		}, function(){
			$('.send-mail', this).stop().fadeOut();
		});
	});