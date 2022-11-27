import Vue from 'vue'
import VueRouter from 'vue-router'

import Index from '../views/Index'
import Login from '../views/Login'
import Register from "../views/Register"
import Sales from "../components/Sales"
import Customer from '../components/Customer'
import Order from '../components/Order'
import Repository from "../components/Repository"
import Dispatch from "../components/Dispatch"
import Good from "../components/Good"

Vue.use(VueRouter);

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Login,
      meta: {
        isLogin: false
      }
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
      meta: {
        isLogin: false
      }
    },
    {
      path: '/index',
      name: 'index',
      component: Index,
      meta: {
        isLogin: true
      },
      children: [
        {
          path: '/index/sales',
          name: 'sales',
          component: Sales
        },
        {
          path: '/index/customer',
          name: 'customer',
          component: Customer
        },
        {
          path: '/index/order',
          name: 'order',
          component: Order
        },
        {
          path: '/index/repository',
          name: 'repository',
          component: Repository
        },
        {
          path: '/index/dispatch',
          name: 'dispatch',
          component: Dispatch
        },
        {
          path: '/index/good',
          name: 'good',
          component: Good
        }
      ]
    }
  ]
});
