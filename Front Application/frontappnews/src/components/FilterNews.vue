<template>
<div  class="fileform">
    <br><br>
    <h1 class="ConfigTitle">News  Configuration </h1>
    <hr class="seperatorlogin">
    <p class="text">If you don't have the structure please Download the file then modify the content then
        upload it again</p>
          <img src="../assets/Download.png"  @click="Download()" class="downloadicone"/><br>
    <input type="file" value="GET FILE" class="bt" ref="uploadfield" @change="UploadFile()" />
    <br>

</div>
</template>
<style>
</style>
<script>

export default{
    data(){
      return{
          file:''
      }
    },
    methods:{
         UploadFile(){
             this.file= this.$refs.uploadfield.files[0] ;
             console.log(this.file);
         },
         Download(){
             alert("hello");
         }
    }
};

</script>