<template>
  <div>

    <div class="inputs">
      <label class="Username-">Username :</label>
     &nbsp;&nbsp; <input id="username" class="userinput" type="text">
      <br>
      <br>
      <br>
      <br>
      <label class="Password-">Password :</label>
      &nbsp;&nbsp; <input id="password" class="passinput" type="password">
      <br>
      <br>
      <label class="Forgot-your-password-">Forgot your password ?</label>
   
        <button class="logbt" @click="login()">
          <label class="Log-Intxt">Log In</label>
        </button>
   
    </div>
    
  </div>
</template>

<style>
@import "../assets/css/login.css";

</style>
<script>

export default {
  name: "Login",

  data() {
    return {
       access:false
    
    };
  },

  methods: {
    login(){
      this.$emit('logged');
 
     }
  }
};
</script>