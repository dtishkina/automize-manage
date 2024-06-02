import { createRouter, createWebHistory } from 'vue-router'
import ReferencesPage from "@/layouts/pages/ReferencesPage.vue";
import AuthPage from "@/layouts/pages/AuthPage.vue";
import LogsPage from "@/layouts/pages/LogsPage.vue";

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/logs',
    component: LogsPage
  },
  {
    path: '/login',
    component: AuthPage,
    meta: { hideNavbar: true }
  },
  {
    path: '/references',
    component: ReferencesPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
