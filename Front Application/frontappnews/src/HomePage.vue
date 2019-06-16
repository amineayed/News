<template>
<div>

<Menu />

<Article />
</div>
</template>
<script>
import Article from "./components/Article";
import Menu from "./components/Menu"
export default{
    name:"homepage",
    components:{
      Article,
      Menu
    }
   
}

</script>
