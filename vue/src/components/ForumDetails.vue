<template>
  <div class="forum-info">
    <h2>{{ forum.forumTitle }}</h2>
    <p>{{ forum.forumContent }}</p>
    <p>{{ forum.createdDate }}</p>
  </div>
</template>

<script>
export default {
  name: 'ForumDetails',
  props: {
    forum: {
      type: Object,
      required: true
    }
  }
}
</script>

<style>

</style>