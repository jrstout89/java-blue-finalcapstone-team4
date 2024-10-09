<template>
    <div class="user-profile">
        <UserProfile/>
        <router-link :to="{ name: 'pets', params: { customerId: $store.state.user.id } }">Pets</router-link>
        <PersonalEvents/>
        <hr/>
        

    </div>
</template>

<script>
import PersonalEvents from '../components/PersonalEvents.vue';
import UserProfile from '../components/UserProfile.vue';

export default {
    components:{
        PersonalEvents,
        UserProfile
    }
}
</script>

<style>


</style>