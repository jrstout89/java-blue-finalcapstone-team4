<template>
  <div class="user-info">
    <h2>{{ user.name }}</h2>
    <p>{{ user.email }}</p>
    <p>{{ user.phone }}</p>
    <p>{{ user.address }}</p>

  </div>
</template>

<script>
import eventService from '../services/eventService';

export default {
  data() {
    return {
      user: {}
    }
  },
  methods: {
    getUserById() {
      return eventService.getUser().then(
        (response) => {
          this.user = response.data;
        }
      );
    }
  }
}
</script>

<style>

</style>