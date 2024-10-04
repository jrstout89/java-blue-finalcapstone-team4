<template>
    <div class="user-profile">
        <!-- <UserProfile :user="user"/> -->
        <h2>Your events</h2>
        <!-- <EventDetails :events="events" @eventSelected="viewEventDetails" /> -->

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