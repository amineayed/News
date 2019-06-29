<template>
  <div class="inputs">
    <label class="Username-">Username :</label>
    <input id="username" class="userinput" type="text" v-model="username">
    <br>
    <br>
    <br>
    <br>
    <label class="Password-">Password :</label>
    <input id="password" class="passinput" type="password" v-model="password">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <label class="Username-">Email :</label>
    <input id="email" class="userinput" type="text" v-model="email">
    <router-link to="/HomePage">
      <button class="logbt" @click="Subscribe()">
        <label class="Log-Intxt">Sign Up</label>
      </button>
    </router-link>
  </div>
</template>

<style>
@import "../assets/css/login.css";
</style>
<script>
import axios from 'axios'

export default {
  name: "SignUp",
  
  data() {
    return {
      username:"",
      password:"",
      email:""
    };
  },

  methods: {
      Subscribe(){
       axios.get("http://localhost:8080/News/user/add/"+this.username+"/"+this.password+"/"+this.email).then((response)=>{
      var user=response.data;
      if(user.iduser==0){
          alert("Login or Email already exists , Please choose another think !");
       }else{
         this.Visibility=!this.Visibility;
         
       }
       });
       
     }
  }
};
</script>