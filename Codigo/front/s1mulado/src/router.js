import { createWebHistory, createRouter } from "vue-router"

import CreateTestLayout from "@/layouts/CreateTestLayout.vue"
import UserProfileComponent from "@/components/user-profile/UserProfileComponent.vue"
import TestHistoryLayout from "@/layouts/TestHistoryLayout.vue"
import LandingPageLayout from "@/layouts/LandingPageLayout.vue"

const routes = [
  { path: "/", component: LandingPageLayout },
  { path: "/home", component: CreateTestLayout, meta: { layout: "default" } },
  { path: "/profile", component: UserProfileComponent, meta: { layout: "default" } },
  { path: "/tests", component: TestHistoryLayout, meta: { layout: "default" } }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;