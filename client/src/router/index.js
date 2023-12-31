import { createRouter, createWebHistory } from 'vue-router'
import ReferencesPage from "@/layouts/pages/ReferencesPage.vue";
import AuthPage from "@/layouts/pages/AuthPage.vue";
import ReportPage from "@/layouts/pages/ReportPage.vue";
import LogsPage from "@/layouts/pages/LogsPage.vue";

const routes = [
  {
    path: '/',
    component: ReferencesPage
  },
  {
    path: '/logs',
    component: LogsPage
  },
  {
    path: '/report',
    component: ReportPage
  },
  {
    path: '/login',
    component: AuthPage
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
