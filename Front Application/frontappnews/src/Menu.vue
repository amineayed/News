<template>

<div>
 
 
  <div class="menu">
      <img src="./assets/img/logoNews.png" class="logo" />
           <br><br>
           <label class="menulinks" @click="Surfing('Article')">All News</label>
       <label class="menulinks" @click="Surfing('FilterNews')">Configure My News</label>
       <label class="menulinks" @click="Surfing('FavoriteArticles')">Favorite Articles</label>
       <label class="menulinks"   @click="Surfing('MyNews')">My News</label>
       <label class="logout" @click="Logout()">Log Out</label>
       </div>
       <br>
       <component :is="Component"></component>
       </div>
</template>

<script>

import MyNews from "./components/MyNews"
import FilterNews from "./components/FilterNews"
import FavoriteArticles from "./components/Favorite"
import SignUpLogin from "./views/SignUpLogin"
import Article from "./components/Article"
 export default{
    name:"Menu",
    components:{
    Article,
      MyNews,
      FilterNews,
      FavoriteArticles,
      SignUpLogin
      
    },
    data(){
        return{
        Component:"Article"
    }

    
},
methods:{
  Surfing(element){
      this.Component=element;
  },Logout(){
    sessionStorage.removeItem("user_ID");
    window.location.reload();


  }
}
}
</script>
<style>
</style>