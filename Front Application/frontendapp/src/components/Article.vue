<template>
<div>
 
   <div class="leftnewsbox"  v-for="NewsTitle in listname" key="NewsTitle"> 
    
                <img src="../assets/img/france24.png"
     
                class="NewsImage">
    
              <label class="News-Title">{{ NewsTitle}}</label>
              <p class="newsdesc">Nec sane haec sola pernicies orientem diversis cladibus adfligebat.
                   Namque et Isauri, quibus est usitatum saepe pacari saepeque inopinis excursibus cuncta miscere,
                 </p>
              <a href="modal.html">
              <img class="Arrow" src=""></a>
          </div>
</div>  
</template>
<script>
import Vue from 'vue'

export default {
      name:"Article",
  data() {
    return {
 
      listname:["firass","ahmed","slim"]
    };
  }
};
</script>
<style>
</style>