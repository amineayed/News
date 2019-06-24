<template>
  <div class="inputs">
    <label class="Username-">Username :</label>
    <input id="username" class="userinput" type="text">
    <br>
    <br>
    <br>
    <br>
    <label class="Password-">Password :</label>
    <input id="password" class="passinput" type="password">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <label class="Username-">Email :</label>
    <input id="username" class="userinput" type="text">
    <router-link to="/HomePage">
      <button class="logbt">
        <label class="Log-Intxt">Sign Up</label>
      </button>
    </router-link>
  </div>
</template>

<style>
@import "../assets/css/login.css";
</style>
<script>
export default {
  name: "SignUp",
  data() {
    return {};
  },

  methods: {
    /* login(){
       this.Visibility=!this.Visibility;
     }*/
  }
};
</script>