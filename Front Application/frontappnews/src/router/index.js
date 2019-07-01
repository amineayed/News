import Vue from "vue";
import Router from "vue-router";

import Login from "@/components/Login";
import MyNews from "@/components/MyNews"
import SignUp from "@/components/SignUp"
import Article from "@/components/Article"
import Favorite from "@/components/Favorite"
Vue.use(Router);

export default new Router({
  routes: [

    {
      path: "/MyNews",
      name: "MyNews",
      component: MyNews
    },
     {
      path: "/LogIn",
       name: "Login",
       component: Login
     },
     {
         path: "/SignUp",
         name: "SignUp",
        component: SignUp
     },
     {
        path: "/Article",
        name: "Article",
        component: Article
     },
     {
        path: "/Favorite",
        name: "Favorite",
        component: Favorite
     }
  
  ]
});
