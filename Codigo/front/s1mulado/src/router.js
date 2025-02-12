import { createWebHistory, createRouter } from "vue-router"

import CreateTestLayout from "@/layouts/CreateTestLayout.vue"
import UserProfileComponent from "@/components/user/UserProfileComponent.vue"
import TestHistoryLayout from "@/layouts/TestHistoryLayout.vue"

const routes = [
  { path: "/", component: CreateTestLayout },
  { path: "/profile", component: UserProfileComponent},
  { path: "/tests", component: TestHistoryLayout}
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;