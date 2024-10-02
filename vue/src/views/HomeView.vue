<template>
  <div class="home">
    <div class="header-container">
    
      <img id="HappyDog" src="../img/HappyDog.gif" alt="HappyDog">
      <img id="welcome" src="../img/welcome.png" alt="welcome">
      <img id="name" src="../img/petPlayPal.png" alt="name">
    
      <nav class="links">
        <router-link to="/login" id="login" class="link">Log in</router-link>
        <router-link to="/users" id="profile" class="link">Profile</router-link>  
      </nav>
    </div>

    <img id="map" src="../img/map.png" alt="map">
    <current-events-vue/>
    <footer>@Copyright</footer>
    <RouterView/>
  </div>
</template>

<script>
import CurrentEventsVue from '../components/CurrentEvents.vue';
import { RouterView } from 'vue-router';
import router from '../router';


export default {
  components:{
    CurrentEventsVue
  }
};
</script>

<style>
.header-container {
    display: flex; 
    align-items: center;
    justify-content: space-between; 
    padding: 5px; 
    border-bottom: 1px solid black;
   }
   #HappyDog, #welcome, #name {
    width: 32%;
    max-width: 100px;
    height: auto;
   }
   #HappyDog {
    max-width: 150px;
    height: auto; 
    margin-right: 10px; 
   }
   #welcome {
    max-width: 450px; 
    height: auto; 
    margin-top: -15px; 
    margin-right: 10px; 
    }
    #name {
    max-width: 600px;
    height: auto; 
    margin-top: 25px;
    margin-left: 20px;
    } 
  .links {
    flex-grow: 1px;
    display: flex;
    align-items: flex-end;
    font-size: 25px;
    margin-left: auto;
    margin-right: 10px;
    margin-top: 10%;
  }
  
  .link {
    margin-left: 50px; 
    text-decoration: none;
  }
  .link:hover {
    text-decoration: underline; 
}
 #map{
    display: block;
    max-width: 100%;
    height: auto;
    margin: 0 auto;
    margin-top: 20px;
    }
</style>
    
    

