import { createWebHistory, createRouter } from "vue-router"

import TestComponent from "@/components/test/TestComponent.vue"

const routes = [
  { path: "/", component: TestComponent },
  { path: "/disputas", component: TestComponent },

]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;