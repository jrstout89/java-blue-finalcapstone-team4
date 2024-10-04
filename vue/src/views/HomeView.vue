<template>
  <!-- <div class="home">
    <div class="header-container">
      <div id="header">
         <router-link to="/">
          <img id="pal" src="../img/petPlayPal.png" alt="welcome" />
        </router-link>  -->
      <!-- </div>
      <div class="tabs">
        <ul> -->
          <!-- <router-link to="/"> <li><a>Home</a></li></router-link>
          <router-link to="/playdates"><li><a>Events</a></li></router-link>
          <router-link to="/forum"> <li><a>Forum</a></li></router-link>
          <router-link to="/user"><li><a>Profile</a></li></router-link> 
          <router-link to="/login"><li class="is-active"><a>Log in</a></li></router-link> -->
        <!-- </ul>
      </div>
    </div> --> 

    <nav class="about">
      <div id="content">
        <img id="dog" src="../img/HappyDog.gif" alt="happy" />
        <h3 id="subtitle">Welcome to Pet Play Pal!</h3>
      </div>
        <p id="text">At Pet Play Pal, we provide a secure and enjoyable space where pets can interact and form friendships. Our dedicated team focuses on enriching activities designed to meet each pet’s unique needs. Join us in cultivating a joyful community for all furry companions!
        </p>
        <router-link to="/pets">pets</router-link>
          <button>View Play Dates</button>
    </nav>
    <footer>
      <p>&copy; 2024 Pet Play Pal. All rights reserved.</p>
    </footer>
</template>

<script>
// import PlayDatesView from './PlayDatesView.vue';
import { RouterView } from 'vue-router';
import router from '../router';


export default {
  // components:{
  //   PlayDatesView
  // },
  data() {
    return {
      isVisible: false,
    };
  },
  methods: {
    toggleVisibility() {
      this.isVisible = !this.isVisible;
    },
  },
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
.tabs{
  margin-left: auto;
  }
#greeting{
    font-size: 3em;
    margin-bottom: 20px;
    font-family: cursive;
  }

.about {
  display: flex;
    flex-direction: column;
    align-items: center; 
    justify-content: center;
    background-image: url('../img/running.jpg' );
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    height: 100vh;
    width: auto;
  }
#content{
    display: flex;
    flex-direction: row;
    width: 100%;
    justify-content: center;
  }
#subtitle{
    font-size: 2em;
    font-family: cursive;
  }
  #dog{
    width: 50px;
    margin-right: 10px;
    height: auto;
  }
  #text{
    font-size: 1.5em;
    font-family: cursive;
  }

</style>
    
    

