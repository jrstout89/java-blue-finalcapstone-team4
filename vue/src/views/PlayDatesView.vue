<template>
    <!-- <div class="home">
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
        </div> -->
        <div class="content-container">
            <div class="left-column">
                <!-- Filter will go here -->
            </div>
            <current-events></current-events>
        </div>
    <!-- </div> -->

</template>

<script>
import CurrentEvents from '../components/CurrentEvents.vue'

export default {
    components:{
        CurrentEvents
    },
    

}
</script>

<style>
.content-container {
    display: flex;
}

.left-column {
    width: 20%; /* Adjust as needed */
    float: left;
    height: 100vh; /* This will make it take the full height of the viewport */
    background-color: #f0f0f0; /* Add a background color for visibility, adjust as needed */
    padding: 1em;
    box-sizing: border-box; /* This makes the padding included in the element's total width and height */
}
</style>