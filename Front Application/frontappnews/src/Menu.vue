<template>
<div>
  <div class="menu">
      <img src="./assets/img/logoNews.png" class="logo" />
           <br><br>
           <label class="menulinks" @click="Surfing('Article')">All News</label>
       <label class="menulinks" @click="Surfing('FilterNews')">Configure My News</label>
       <label class="menulinks">Favorite Articles</label>
       <label class="menulinks"   @click="Surfing('MyNews')">My News</label>
       <label class="logout">Log Out</label>
       </div>
       <br>
       <component :is="Component"></component>
       </div>
</template>

<script>
import Article from "./components/Article"
import MyNews from "./components/MyNews"
import FilterNews from "./components/FilterNews"
export default{
    name:"Menu",
    components:{
      Article,
      MyNews,
      FilterNews
    },
    data(){
        return{
        Component:"Article"
    }
},
methods:{
  Surfing(element){
      this.Component=element;
  }
}
}
</script>
<style>
</style>