<template>
  <div class="user-info">
    <h2>{{ user.firstName }}</h2>
    <p>{{ user.lastName }}</p>
    <p>{{ user.email }}</p>
    <p>{{ user.phoneNumber }}</p>
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
  created() {
      return eventService.getUser().then(
        (response) => {
          this.user = response.data;
        }
      );
    }
  }
</script>

<style>

</style>