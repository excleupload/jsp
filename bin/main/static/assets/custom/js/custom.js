/* Hide Loading Box (Preloader) */
function handlePreloader() {
    if($('.preloader').length){
        $('.preloader').delay(500).fadeOut(500);
    }
}


/* When document is loading, do */
$(window).on('load', function() {
    handlePreloader();
});


 /*DateDropper - TimeDropper*/
$(document).ready(function(){
    $('.datedropper').dateDropper({
    	
    });

    $('.timedropper').timeDropper({
        format: "hh:mm A",
        meridians: !0,
        'setCurrentTime' : false
    });
});


/* OnScroll Header Hide */
window.onscroll = function() {myFunction()};
	var header = document.getElementById("header");
	var navbar = document.getElementById("navbar");
	var sticky = navbar.offsetTop;

	function myFunction() {
	if (window.pageYOffset >= sticky) {
		navbar.classList.add("sticky");
		header.classList.add("hide")
	} else {
		navbar.classList.remove("sticky");
		header.classList.remove("hide")
	}
}