<template>
    <div class="home">
        <div class="header-container">
            <div id="header">
                <router-link to="/">
                <img id="pal" src="src\img\PetPlayPals.png" alt="welcome" />
                </router-link> 
            </div>
            <div class="tabs">
                <ul>
                <router-link to="/"> <li><a>Home</a></li></router-link>
                <router-link to="/playdates"><li><a>Events</a></li></router-link>
                <router-link to="/forum"> <li><a>Forum</a></li></router-link>
                <router-link to="/user"><li><a>Profile</a></li></router-link> 
                <!-- <router-link to="/login"><li class="is-active"><a>Log in</a></li></router-link> -->
                <router-link to="/login" v-if="!isLoggedIn"><li class="is-active"><a>Login</a></li></router-link>
                <router-link to="/logout" v-else><li class="is-active"><a>Logout</a></li></router-link>
                </ul>
            </div>
        </div>
    </div>

    <div class="user-profile">
        <UserProfile :user="user"/>
        <h2>Your events</h2>
        <EventDetails :events="events" @eventSelected="viewEventDetails" />

    </div>
</template>

<script>
import EventDetails from '../components/EventDetails.vue';
import UserProfile from '../components/UserProfile.vue';

export default {
    components:{
        EventDetails,
        UserProfile
    },
    props: {
        user: Object,
        events: Array
    },
    methods: {
        viewEventDetails(event){
            this.$emit('view-event-details', event);
        },
        viewUserProfile(user){
            this.$emit('view-user-profile', user);
        }
    }
}
</script>

<style>

</style>