$(function(){
	
	$("body").on("click",".nl-list1 h2",function(){
		if ($(this).siblings("img").hasClass("rotateDown")) {
			$(this).siblings("img").removeClass("rotateDown").addClass("rotateUp");
			$(this).siblings(".nl-list2").stop().slideUp(500);
		} else {
			$(".nl-list1 img").each(function(){
				if ($(this).hasClass("rotateDown")) {
					$(this).removeClass("rotateDown").addClass("rotateUp");
				}
			})
			$(this).siblings("img").removeClass("rotateUp").addClass("rotateDown");
			$(".nl-list2").stop().slideUp(500);
			$(this).siblings(".nl-list2").stop().slideDown(500);
		}
		
	})
})