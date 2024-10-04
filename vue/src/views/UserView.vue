<template>
    <div class="user-profile">
        <UserProfile/>
        <PersonalEvents/>
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