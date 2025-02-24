const btnMenu = document.getElementById("navbar-menu");
const menu = document.getElementById("side-menu");
const overlay = document.getElementById("overlay");
let isOpen;

function contentResize() {
	const target = document.getElementById("mainContent");
	const navbar = document.getElementById("lb_navbar");
	if (target != null) {
		let heightValue = parseFloat(window.getComputedStyle(navbar).height);
		let spaceValue = parseFloat(window.getComputedStyle(navbar).marginBottom);
		target.style.paddingTop = (heightValue + spaceValue) + "px";
		console.log("Margin has Updated: ", target.style.paddingTop);
		return target;
	} else {
		console.log("Target is Empty");
	}
}

function smOpen() {
	overlay.classList.remove("d-none");
	overlay.classList.add("d-block");
	menu.classList.remove("lb_disabled");
	menu.classList.add("lb_active");
	isOpen = true;
	smResize();
}

function smClose() {
	overlay.classList.remove("d-block");
	overlay.classList.add("d-none");
	menu.classList.remove("lb_active");
	menu.classList.add("lb_disabled");
	isOpen = false;
}

function smResize() {
	const navbarHeight = parseFloat(window.getComputedStyle(document.getElementById("lb_navbar")).height);
	const menuMargin = navbarHeight / 10;
	menu.style.paddingTop = navbarHeight + menuMargin + "px";
	console.log("Padding Top is: " + menu.style.paddingTop);
}

btnMenu.addEventListener("click", () => {
	if (isOpen) {
		smClose();
	} else {
		smOpen();
	}
});
overlay.addEventListener("click", smClose);
document.addEventListener("DOMContentLoaded", () => {
	contentResize();
	smResize();
});
window.addEventListener("resize", () => {
	contentResize();
	smResize();
});


