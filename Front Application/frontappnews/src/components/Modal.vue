<template>

<div class="Mymodal" v-if="visib">
  
  <div>
      <br>
    <label class="modaltitle News-Title">News Title</label>
  <br>
  <label class="closeicone News-Title" @click="close">x</label></div>
  <hr>
  <br>
<div>   
  <img src="../assets/img/france24.png"  class="articleimg"/>
  <br>
  <br>
    <hr>
  <p>Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression.
     Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500,
      quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser 
      un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles,
       mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié.
        Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant
         des passages du Lorem Ipsum, et, plus récemment,
     par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.</p>  <br>
    
  </div>
</div>

</template>


<script>
   export default{
     name:"Modal",
     data(){
       return{
       visib:false
       }
     },
      props:{
     seen:{
       type:Boolean,
       required:true,
       default:false,
     }
    
       },
       created(){
         
        this.visib = this.seen ;
        this.$emit("Modalvisibility",false);
         
       }
   }
</script>
<style>
</style>