import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import About from '../views/AboutView.vue'
import Product from '../views/ProductView.vue'
import ProductDetail from '../views/ProductDetail.vue'
import LoginView from "@/views/UserViews/LoginView.vue";
import RegisterView from "@/views/UserViews/RegisterView.vue";
import SellerPage from "@/components/SellerPage.vue";
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  }
  , {
    path: '/detail/:id',
    name: 'ProductDetail',
    component: ProductDetail
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: About
  },
  {
    path: '/LoginView',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: LoginView
  },
  {
    path: '/RegisterView',
    name: 'register',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: RegisterView
  },

  {
    path: '/product',
    name: 'Product',
    component: Product
  },{
    path: '/seller/:seller',
    name: 'Seller',
    component: SellerPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
