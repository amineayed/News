<template>

<div    style="display: table-cell;" class="backgroundcolor">

   <div class="leftnewsbox"  v-for="News in TitleAR" :key="News.ID"> 
     <div @click="addFavorite(News.ID)">
              <img src="../assets/img/addbt.png" class="addbt"  />
     </div>
     
            <img class="Arrow" src="../assets/img/arrow.png" @click="OpenNewTAB(News.Link)" >
              <br >
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
 name:"Article",
 components:{
  
 },
  data() {
    return {
        TitleAR:[],
      
    };
  },
  created(){      
    
     axios.get("http://localhost:8080/News/articles/all").then((response)=>{
              var articles=response.data;
              articles.forEach(element => {  
                
               this.TitleAR.push(element);
                });
                
        
      });
         
             },
             methods:{
            
                 OpenNewTAB(link){
                  var win = window.open(link,'_blank')
                   win.focus();
                },
                addFavorite(ArticleId){
                   axios.get("http://localhost:8080/News/setfavorites/"+sessionStorage.getItem("user_ID")+"/"+ArticleId).then((response)=>{
              var result=response.data;
              if(result.message!='ok'){
                alert(result.message);
              }
        
      }); 
                }
          
             }
 

};
</script>
<style>
 @import '../assets/css/homepage.css';
</style>