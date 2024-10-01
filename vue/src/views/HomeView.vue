<template>
  <div class="home">
    <div class="header-container"> 
     <img id="HappyDog" src="../img/HappyDog.gif" alt="HappyDog">
      <img id="header" src="../img/Header.png" alt="Header">
    </div>
    <nav class="links">
      
      <router-link id="" to="/playDates">Current Events</router-link> 
      <router-link to="">Search Events</router-link>
      <router-link to="/login">Log in</router-link>
    </nav>
    <br/>
    <img src="../img/playingDogs.jpg" alt="Playing Dogs">
    <footer>@Copyright</footer>
    <RouterView/>
  </div>
</template>

<script>
import { RouterView } from 'vue-router';
import router from '../router';

export default {
};
</script>

<style>
.links{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  border-bottom: 1px solid black;
  border-right: 1px solid black;
  font-size: 30px;
}
.header-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  border-bottom: 1px solid black;
}
#happyDog {
  display: flex;
  flex-direction: left;
}
  #header {
  width: 518px;
}
  .home {
   display: flex;
   flex-direction: column;
  
  }
  router-link {
    margin: 10px;
    display: block;
    border: 10px;
    padding: 10px;
  }
 #HappyDog{
  display: flex;
  height: 200px;
  width: 200px;
 }
</style>