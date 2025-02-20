function forgeContent() {
	const target = document.getElementById("forgeContent");
	const navbar = document.getElementById("lb_navbar");
	const footer = document.getElementById("lb_footer")
	
	let heightValue = parseFloat(window.getComputedStyle(navbar).height);
	let spaceValue = parseFloat(window.getComputedStyle(navbar).marginBottom);
	
	target.style.paddingTop = (heightValue + spaceValue) + "px";
	console.log("Margin has Updated: ", target.style.paddingTop);
	
	heightValue = parseFloat(window.getComputedStyle(footer).height);
	spaceValue = parseFloat(window.getComputedStyle(footer).paddingTop);
	
	target.style.paddingBottom = (heightValue + spaceValue) + "px";
}

document.addEventListener("DOMContentLoaded", forgeContent);
window.addEventListener("resize", forgeContent);
