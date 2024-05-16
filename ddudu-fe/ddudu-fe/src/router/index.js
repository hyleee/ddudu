import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '@/components/user/Login.vue'
import SignUp from '@/components/user/SignUp.vue'
import PlanView from '@/views/PlanView.vue'
import DiaryDetailView from '@/views/DiaryDetailView.vue'




const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignUp
    },
    {
      path: '/plan',
      name: 'plan',
      component: PlanView
    },
    {
      path: '/diary',
      name: 'diary',
      component: DiaryDetailView,
    },
    
  ]
})

export default router
