import Vue from "vue";
import Router from "vue-router";
import HomePage from "./HomePage";
import Login from "./components/Login";


Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/HomePage",
      name: "HomePage",
      component: HomePage
    },
    // {
      // path: "/",
      // name: "Login",
      // component: Login
    // },
  
  ]
});
