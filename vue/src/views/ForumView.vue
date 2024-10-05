<template>
    <div class="forum-header">
        <h1>Welcome to the Pet Play Pals Forum!!</h1>
        <p>Share your fur baby stories, talk about upcoming play dates, and more.</p>
    </div>
    <ForumDetails/>
    <div>
        <h2>Forum Posts</h2>
        <p>Post your own stories and comments below!</p>
    </div>
</template>

<script>
import ForumDetails from '../components/ForumDetails.vue';

export default {
    components: {
        ForumDetails
    }
}
</script>

<style>

</style>