<template>
  <div>
    <div class="logform" v-if="loginform">
      <div class="labeldiv2hover divongletleft">
        <br>
        <label class="Log-InHeader labelhover" @click="Component='Login'">Log In</label>
      </div>
      <div class="labeldivhover divonglet">
        <br>
        <label class="Sign-UpHeader labelhover" @click="Component='SignUp'">Sign Up</label>
    
      </div>
  <hr class="seperatorlogin">


      <component :is="Component" @logged="logged"></component>
    </div>
       <component :is="homepage"></component>
  </div>
</template>
<script>
import SignUp from "../components/SignUp";
import Login from "../components/Login";
import Menu from "../Menu";
export default {
  components: {
    SignUp,
    Login,
    Menu
  },
  data() {
    return {
      Component: "Login",
      homepage:"",
      loginform:true
    };
  },
  methods:{
    logged(){
    
      this.homepage="Menu"
      this.loginform=false;
    }
  }
};
</script>