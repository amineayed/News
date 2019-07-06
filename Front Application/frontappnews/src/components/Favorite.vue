<template>

<div    >
  <br><br>
<center><h1 v-if="message!=''" style="color:white;">{{message}}</h1></center>
   <div class="leftnewsbox"  v-for="News in TitleAR" :key="News.ID"> 
      <div @click="RemoveFavorite(News)">
              <img src="../assets/img/removebt.png" class="addbt"  />
     </div>
            <img class="Arrow" src="../assets/img/arrow.png" @click="OpenNewTAB(News.Link)" >
              <br>
                 <img v-if="News.Image !='NO_IMAGE'" v-bind:src="News.Image" 
              
                class="NewsImage">
              <img v-else-if="News.Image =='NO_IMAGE'"  src="../assets/img/BBC.png" class="NewsImage">
          
<p class="News-Title" @click="OpenNewTAB(News.Link)">{{ News.Title }}</p>
              <hr class="articleseperator">
              <p class="newsdesc">{{ News.Description || "No Description" }} 
               
                 </p>
           <label class="newsdesc"> Source : {{ News.Source }}</label>
          
          </div>
      
</div> 

</template>
<script>

import axios from "axios"

export default {
 name:"Favorite",
 components:{
  
 },
  data() {
    return {
        TitleAR:[],
      message:'',
      Component:"Favorite"
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
                },RemoveFavorite(Article){
                   axios.get("http://localhost:8080/News/RemoveFavoriteArticle/"+sessionStorage.getItem("user_ID")+"/"+Article.ID);
                  console.log(this.TitleAR.indexOf(Article));
                  this.TitleAR.splice(this.TitleAR.indexOf(Article),1);
                  
                  if(this.TitleAR.length==0){
                    this.message="No Favorite Articles !";
                  }
               }
          
             }
 

};
</script>
<style>
 @import '../assets/css/homepage.css';
</style>