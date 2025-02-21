function forgeContent() {
	let target = contentResize();
	const footer = document.getElementById("lb_footer");	
	let heightValue = parseFloat(window.getComputedStyle(footer).height);
	let spaceValue = parseFloat(window.getComputedStyle(footer).paddingTop);
	
	target.style.paddingBottom = (heightValue + spaceValue) + "px";
}

document.addEventListener("DOMContentLoaded", forgeContent);
window.addEventListener("resize", forgeContent);
