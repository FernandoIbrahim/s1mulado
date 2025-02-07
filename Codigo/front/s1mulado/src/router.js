import { createWebHistory, createRouter } from "vue-router"

import CreateTestComponent from "@/components/test/CreateTestComponent.vue"
import UserProfileComponent from "@/components/user/UserProfileComponent.vue"
import TestHistoryLayout from "@/layouts/TestHistoryLayout.vue"

const routes = [
  { path: "/", component: CreateTestComponent },
  { path: "/profile", component: UserProfileComponent},
  { path: "/tests", component: TestHistoryLayout}
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;