<template>
  <div class="home">
    <img id="HappyDog" src="../img/HappyDog.gif" alt="HappyDog">
    <img id="header" src="../img/Header.png" alt="Header">
    <router-link to="/login">Login</router-link>
    <router-link to="/playDates">Current Events</router-link> 
    <br/>
    <img src="../img/playingDogs.jpg" alt="Playing Dogs">
    <!-- <router-link to="">Search Events</router-link> -->
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
  .home {
    text-align: center;
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