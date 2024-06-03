import { createRouter, createWebHistory } from 'vue-router'
import ReferencesPage from "@/layouts/pages/ReferencesPage.vue";
import AuthPage from "@/layouts/pages/AuthPage.vue";
import LogsPage from "@/layouts/pages/LogsPage.vue";
import ErrorPage from "@/layouts/pages/ErrorPage.vue";

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
    meta: { hideNavbar: true, showNoInfoHeader: true}
  },
  {
    path: '/references',
    component: ReferencesPage
  },
  {
    path: '/error',
    component: ErrorPage,
    meta: { hideNavbar: true, showNoInfoHeader: true}
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
