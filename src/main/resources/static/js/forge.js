const btnSearch = document.getElementById("btnSearch");

btnSearch.addEventListener("click", () => {
	document.getElementById("kw").value = document.getElementById("search_kw").value;
	document.getElementById("searchForm").submit();
});