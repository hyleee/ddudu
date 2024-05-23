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
import BoardView from "@/views/BoardView.vue";
import UserSearchView from "@/views/UserSearchView.vue";
import ArticleView from "@/views/ArticleView.vue";
import RankView from "@/views/RankView.vue";
import ArticleCreateView from "@/views/ArticleCreateView.vue";

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
    {
      path: "/board",
      name: "board",
      component: BoardView,
    },
    {
      path: "/rank",
      name: "rank",
      component: RankView,
      children: [
        {
          path: ":bodyPart",
          name: "BodyPartRank",
          component: RankView,
        },
      ],
    },
    {
      path: "/usersearch",
      name: "usersearch",
      component: UserSearchView,
    },
    {
      path: "/article/:articleId",
      name: "article",
      component: ArticleView,
    },

    {
      path: "/article",
      name: "articleCreate",
      component: ArticleCreateView,
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
