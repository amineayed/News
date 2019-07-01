<template>

<div    class="backgroundcolor">
<h1 v-if="message!=''">{{message}}</h1>
   <div class="leftnewsbox"  v-for="News in TitleAR" :key="News.ID"> 
      <div @click="RemoveFavorite(News.ID)">
              <img src="../assets/img/removebt.png" class="addbt"  />
     </div>
            <img class="Arrow" src="../assets/img/arrow.png" @click="OpenNewTAB(News.Link)" >
              <br>
                <img v-bind:src="News.Image"
     
                class="NewsImage">
          
<p class="News-Title" @click="OpenNewTAB(News.Link)">{{ News.Title }}</p>
              <hr class="articleseperator">
              <p class="newsdesc">{{ News.Description || "No Description" }} 
               
                 </p>
           <label class="newsdesc"> Source : {{ News.Source }}</label>
          
          </div>
     
</div> 

</template>
<script>

import axios from 'axios'

export default {
 name:"Favorite",
 components:{
  
 },
  data() {
    return {
        TitleAR:[],
      message:''
    };
  },
  created(){      
     axios.get("http://localhost:8080/News/GetFavoriteArticles/"+sessionStorage.getItem("user_ID")).then((response)=>{
              var articles=response.data;
              if(articles.message==null){
              articles.forEach(element => {    
               this.TitleAR.push(element);
                });
              }else{
this.message=articles.message;
              }
                
        
      });   
             },
             methods:{
            
                 OpenNewTAB(link){
                  var win = window.open(link,'_blank')
                   win.focus();
                },RemoveFavorite(ArticleId){
                   axios.get("http://localhost:8080/News/RemoveFavoriteArticle/"+sessionStorage.getItem("user_ID")+"/"+ArticleId);
                  
               }
          
             }
 

};
</script>
<style>
 @import '../assets/css/homepage.css';
</style>