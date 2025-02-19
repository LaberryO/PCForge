function forgeContent() {
	const target = document.getElementById("forgeContent");
	const source = document.getElementById("lb_navbar");
	
	const heightValue = parseFloat(window.getComputedStyle(source).height);
	const mbValue = parseFloat(window.getComputedStyle(source).marginBottom);
	
	target.style.paddingTop = (heightValue + mbValue) + "px";
	console.log("Margin has Updated: ", target.style.paddingTop);
}

document.addEventListener("DOMContentLoaded", forgeContent);
window.addEventListener("resize", forgeContent);
