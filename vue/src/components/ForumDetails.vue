<template>
  <div class="forum-info">
    <h2>{{ forum.forumTitle }}</h2>
    <p style="display: inline">Posted by: {{ forum.username }}</p>
    <p style="display: inline; margin-left: 5px;">on: {{ forum.createdDate }}</p>
    <p>{{ forum.forumContent }}</p>
    
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
.forum-info {
  background-color: #f9f9f9;
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.forum-info h2 {
    font-size: 2.5rem;
    margin-bottom: 10px;
    color: #333;
  }

</style>