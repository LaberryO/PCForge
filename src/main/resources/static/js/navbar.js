const btnMenu = document.getElementById("navbar-menu");
const menu = document.getElementById("side-menu");
const overlay = document.getElementById("overlay");

function smOpen() {
	overlay.classList.remove("d-none");
	overlay.classList.add("d-block");
	menu.classList.remove("lb_disabled");
	menu.classList.add("lb_active");
}

function smClose() {
	overlay.classList.remove("d-block");
	overlay.classList.add("d-none");
	menu.classList.remove("lb_active");
	menu.classList.add("lb_disabled");
}

btnMenu.addEventListener("click", smOpen)
overlay.addEventListener("click", smClose)