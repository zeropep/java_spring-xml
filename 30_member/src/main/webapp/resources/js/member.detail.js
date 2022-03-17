document.addEventListener("click", (e) => {
    if (e.target.id == "modBtn") {
        e.preventDefault();
        let modBtn = document.getElementById("modBtn");
        modBtn.innerText = "Submit";
        modBtn.setAttribute("type", "submit");
        modBtn.id = "sbmBtn";
        document.getElementById("pwd").readOnly = false;
        document.getElementById("nickName").readOnly = false;
    }
});