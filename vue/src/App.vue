<template>
  <div class="home">
        <div class="header-container">
            <div id="header">
                <router-link to="/">
                <img id="pal" src=".\img\PetPlayPals.png" alt="welcome" />
                </router-link> 
            </div>
            <div class="tabs">
                <ul>
                <router-link to="/"> <li><a>Home</a></li></router-link>
                <router-link to="/playdates"><li><a>Events</a></li></router-link>
                <router-link to="/forum"> <li><a>Forum</a></li></router-link>
                <router-link to="/user"><li><a>Profile</a></li></router-link> 
                 <nav>
                <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
                </nav>
                </ul>
            </div>
        </div>
    </div>
    <router-view />
</template>