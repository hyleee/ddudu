import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import Login from "@/components/user/Login.vue";
import SignUp from "@/components/user/SignUp.vue";
import PlanView from "@/views/PlanView.vue";
import DiaryDetailView from "@/views/DiaryDetailView.vue";
import { useLoginStore } from "@/stores/loginStore";
import ExerciseDetailView from "@/views/ExerciseDetailView.vue";
import MyProfileView from "@/views/MyProfileView.vue";
import BodyInfoView from "@/views/BodyInfoView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: Login,
    },
    {
      path: "/signup",
      name: "signup",
      component: SignUp,
    },
    {
      path: "/plan/:userId/:exerciseDate",
      name: "plan",
      component: PlanView,
      beforeEnter(to, from) {
        // to.name === 'private'
      },
    },
    {
      path: "/diary",
      name: "diary",
      component: DiaryDetailView,
    },
    {
      path: "/plan/detail/:planId",
      name: "exerciseDetail",
      component: ExerciseDetailView,
    },
    {
      path: "/profile",
      name: "userProfile",
      component: MyProfileView,
    },
    {
      path: "/bodyinfo",
      name: "bodyinfo",
      component: BodyInfoView,
    },
  ],
});

router.beforeEach((to, from) => {
  const loginStore = useLoginStore();

  if (to.name === "plan" && loginStore.accessToken === "") {
    return { name: "login" };
  }
  // 탐색을 취소하려면 명시적으로 false를 반환해야 함.
  return true;
});

export default router;
