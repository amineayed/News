<template>
<div>

<Menu />
<MyNews />
<Article />
</div>
</template>
<script>
import Article from "./components/Article";
import Menu from "./components/Menu";
import MyNews from "./components/MyNews";
export default{
    name:"homepage",
    components:{
      Article,
      Menu,
      MyNews
    }
   
}

</script>
