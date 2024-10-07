<template>
  <div class="forum-header">
    <h1>Welcome to the Pet Play Pals forum!!</h1>
    <ul>
        <li v-for="forum in forums" :key="forum.id">
            <router-link :to="{ name: 'forumDetails', params: {id: forum.id} }">
                <h2>{{ forum.forumTitle }}</h2>
            </router-link>
        </li>
    </ul>
  </div>
</template>

<script>
import ForumService from '../services/ForumService';

export default {
    data() {
        return {
            forums: []
        }
    },
    created() {
        return ForumService.getForums().then(
            (response) => {
            this.forums = response.data;
            }
        );
    }
}
</script>

<style>

</style>