import { createApp } from "vue"
import "./assets/index.css"
import router from "./router"
import App from "./App.vue"
import { createPinia } from "pinia"
import Toast, {POSITION} from "vue-toastification";
import "vue-toastification/dist/index.css";

const pinia = createPinia();

const toastOptions = {
    transition: "Vue-Toastification__bounce",
    maxToasts: 20,
    newestOnTop: true,
    position: POSITION.BOTTOM_RIGHT
};


createApp(App)
.use(pinia)
.use(router)
.use(Toast, toastOptions)
.mount("#app")


