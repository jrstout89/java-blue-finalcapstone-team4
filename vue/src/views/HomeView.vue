<template>
  <div class="home">
    <div class="header-container">
      <div id="header">
        <router-link to="/">
          <img id="pal" src="../img/petPlayPal.png" alt="welcome" />
        </router-link> 
      </div>
      <div class="tabs">
        <ul>
          <router-link to="/"> <li><a>Home</a></li></router-link>
          <router-link to="/playdates"><li><a>Events</a></li></router-link>
          <router-link to="/forum"> <li><a>Forum</a></li></router-link>
          <router-link to="/user"><li><a>Profile</a></li></router-link> 
          <router-link to="/login"><li class="is-active"><a>Log in</a></li></router-link>
        </ul>
      </div>
    </div>

    <div class="events">
      <div class="search-bar">
        <h1 id="greeting">Welcome!</h1>
        <input type="text" v-model="search" placeholder="Search Events">
        <button @click="searchEvents">Search</button>
      </div>
      <PlayDatesView v-if="isVisible" />
    </div>
    <nav class="about">
      <img src="../img/HappyDog.gif" alt="happy" />
      <h3 id="subtitle">Welcome to Pet Play Pal!</h3>
      <p id="content">At Pet Play Pal, our mission is to provide a safe, fun, and nurturing environment where pets can socialize, play, and thrive. We understand that pets need companionship and exercise to stay happy and healthy. Our dedicated team is committed to offering engaging play sessions, socialization opportunities, and enrichment activities that cater to each pet's unique needs. Whether it’s a day of play, training, or simply making new friends, we strive to create a joyful experience for every furry family member. Join us in fostering a vibrant community where pets can connect, explore, and have a paw-sitive time!
      </p>
      
    </nav>
  </div>
</template>

<script>
import PlayDatesView from './PlayDatesView.vue';
import { RouterView } from 'vue-router';
import router from '../router';


export default {
  components:{
    PlayDatesView
  },
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
.events{
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
#greeting{
    font-size: 3em;
    margin-bottom: 20px;
    font-family: cursive;
}
.search-bar{ 
    align-items: center;
    justify-content: center;
    margin-bottom: 50px;
    padding-bottom: 30%;
}
.about {
    display: flex; /* Enables flexbox layout */
    align-items: flex-start; /* Aligns items at the start vertically */
    padding: 20px; /* Adds padding around the section */
    background-color: #f9f9f9; /* Light background for contrast */
    border-radius: 10px; /* Rounded corners */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
    margin: 20px; /* Adds margin around the section */
}




</style>
    
    

