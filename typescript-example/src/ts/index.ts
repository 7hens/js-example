import App from "./App";

let app = App.instance;
document.body.appendChild(app.view);
window.addEventListener("resize", () => {
    app.resize();
});

app.bootstrap();