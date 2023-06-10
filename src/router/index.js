import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import Details from '../views/Details.vue'; // Modify this line to match the location of your Details component

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import(/* webpackChunkName: "about" */ '../views/Manage.vue'),
    redirect: "home",
    children: [
      { path: 'home', name: 'Home', component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue')},
      // { path: 'login', name: 'Login', component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')},
      { path: 'user', name: 'User', component: () => import(/* webpackChunkName: "about" */ '../views/User.vue')},
      { path: 'detail', name: 'Detail', component: () => import(/* webpackChunkName: "about" */ '../views/Details.vue')},
      { path: 'filmform', name: 'FilmForm', component: () => import(/* webpackChunkName: "about" */ '../views/Filmform.vue')},
      { path: 'top', name: 'TopMovies', component: () => import(/* webpackChunkName: "about" */ '../views/TopMovies.vue')},
      {
        path: 'details/:movieId',
        name: 'Details',
        component: Details,
      },
      { path: 'like', name: 'Like', component: () => import(/* webpackChunkName: "about" */ '../views/Like.vue')},



    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name:'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/register',
    name:'Register',
    component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

NProgress.configure({
  easing: 'ease',
  speed: 400,
  showSpinner: false,
  trickleSpeed: 200,
  minimum: 0.3
})



router.beforeEach((to, from, next) => {
  // NProgress.done()
  NProgress.start()
  next()
})

router.afterEach(() => {
  NProgress.done()
})

export default router
