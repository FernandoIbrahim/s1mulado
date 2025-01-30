import { createApp } from "vue"
import "./assets/index.css"
import router from "./router"
import App from "./App.vue"
import { createPinia } from "pinia"

const pinia = createPinia();

createApp(App)
.use(pinia)
.use(router)
.mount("#app")
