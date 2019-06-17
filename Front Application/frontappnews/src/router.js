import Vue from "vue";
import Router from "vue-router";
import HomePage from "./HomePage";
import Login from "./components/Login";
import MyNews from "./components/MyNews"

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/HomePage",
      name: "HomePage",
      component: HomePage
    },
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
