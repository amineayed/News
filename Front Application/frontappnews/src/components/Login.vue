<template>
<div  v-if="Visibility" >

       <div class="logform"> 
            
            <div class="labeldiv2hover divongletleft">
                    <br>
           <label class="Log-InHeader labelhover">Log In</label>
           </div>
           <div class="labeldivhover divonglet">
               <br>
           <label class="Sign-UpHeader labelhover">Sign Up</label>
           </div>
           <br><br><br>
            <hr>
           <br> <br> <br> <br>
           <div class="inputs">
           <label class="Username-">Username : </label>
           <input id="username" class="userinput" type="text"/>
           <br> <br> <br> <br>
           <label class="Password-">Password : </label>
           <input id="password" class="passinput" type="password"/><br><br>
           <label class="Forgot-your-password-">Forgot your password ?</label>
         <router-link to="/HomePage" > <button class="logbt" @click="login" ><label class="Log-Intxt">  Log In </label></button></router-link> 
       
</div>

       </div>

</div>

</template>

<style>


  @import '../assets/css/login.css';
</style>
<script>
export default{
    name:"Login",
    data(){
      return{
       Visibility:true,
      }
    },
    
methods:{
     login(){
       this.Visibility=!this.Visibility;
     }
}
}
</script>