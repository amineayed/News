import Vue from "vue";
import Router from "vue-router";

import Login from "./components/Login";
import MyNews from "./components/MyNews"

Vue.use(Router);

export default new Router({
  routes: [

    {
      path: "/MyNews",
      name: "MyNews",
      component: MyNews
    },
    // {
      // path: "/",
      // name: "Login",
      // component: Login
    // },
  
  ]
});
