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
    </div>
</template>

<script>
import UserProfile from '../components/UserProfile.vue'

export default {

}
</script>

<style>

</style>