<template>
  <div>

    <div class="inputs">
      <label class="Username-">Username :</label>
     &nbsp;&nbsp; <input id="username" class="userinput" type="text" v-model="username">
      <br>
      <br>
      <br>
      <br>
      <label class="Password-">Password :</label>
      &nbsp;&nbsp; <input id="password" class="passinput" type="password" v-model="password">
      <br>
      <br>

   
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
import axios from 'axios'
export default {
  name: "Login",

  data() {
    return {
       access:false,
       username:'',
       password:''
    
    };
  },

  methods: {
    login(){
      axios.get("http://localhost:8080/News/login/"+this.username+"/"+this.password).then((response)=>{
              var user=response.data;
              if(user.message==null){
                sessionStorage.setItem("user_ID",user.iduser);
                sessionStorage.setItem("login",user.login);
                this.$emit('logged');
      }else{
        alert("username or password invalide !");
      }
      
 
      });
  }
}
};
</script>