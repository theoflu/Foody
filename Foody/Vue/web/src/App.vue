<template>
  <div>
  <nav>
<home-view/>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/product">Product List</router-link> |
    <router-link to="/LoginView">Login</router-link>|
    <router-link to="/RegisterView">Register</router-link>

  </nav>
  </div>
    <router-view/>

</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
<script>

import {defineComponent} from "vue";
import HomeView from "@/views/HomeView.vue";

export default defineComponent({
  components: {HomeView}
})
</script>