<template>
  <div class="home">
        <header class="header-container">
            <div id="header">
                <router-link to="/">
                <img id="pal" src=".\img\logo.png" alt="welcome" />
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
        </header>
        <main class="background">
            <router-view />
        </main>
    </div>
</template>

<style>
.home {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

.header-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 5px;
    border-bottom: 1px solid black;
    background-color: rgba(255, 255, 255, 0.9);
}

.background {
  background-image: url(https://img.freepik.com/premium-vector/pets-paw-pattern-bones-flying-saucer-animal-footsteps-seamless-texture-pet-store-background_657999-3041.jpg?w=740);
  background-size: cover;
  background-position: center;
  flex-grow: 1;
  min-height: 100vh;
  padding: 20px;
}
</style>