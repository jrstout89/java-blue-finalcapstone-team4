<template>
  <div class="home">
        <div class="header-container">
            <div id="header">
                <router-link to="/">
                <img id="pal" src="src\img\petPlayPal.png" alt="welcome" />
                </router-link> 
            </div>
            <div class="tabs">
                <ul>
                <router-link to="/"> <li><a>Home</a></li></router-link>
                <router-link to="/playdates"><li><a>Events</a></li></router-link>
                <router-link to="/forum"> <li><a>Forum</a></li></router-link>
                <router-link to="/user"><li><a>Profile</a></li></router-link> 
                <!-- <router-link to="/login"><li class="is-active"><a>Log in</a></li></router-link> -->
                <router-link to="/login" v-if="!isLoggedIn"><li class="is-active"><a>Log in</a></li></router-link>
                <router-link to="/logout" v-else><li class="is-active"><a>Log out</a></li></router-link>
                </ul>
            </div>
        </div>
    </div>
  <div id="capstone-app">
    <div id="nav">
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp; -->
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
