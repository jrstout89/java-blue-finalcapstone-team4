<template>
    <div class="user-profile">
        <UserProfile/>
        <PersonalEvents/>
        <hr/>
        <router-link :to="{ name: 'pets', params: { customerId: $store.state.user.id } }">Pets</router-link>

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