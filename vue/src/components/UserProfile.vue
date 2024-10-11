<template>
  <div class="user-info">
    <h1 id="header">USER PROFILE</h1>
    <p><strong>{{ user.firstName }} {{ user.lastName }}</strong></p>
    <p><strong>{{ user.email }}</strong></p>
    <p><strong>{{ user.phoneNumber }}</strong></p>
    <nav>
    <router-link :to="{ name: 'pets', params: { customerId: $store.state.user.id } }">
            <button class="button is-info">Your Pets</button>
        </router-link> 
        &nbsp; &nbsp;
        <router-link to="/events/add-event">
                <button class="button is-success">Add Event</button>
            </router-link>
          </nav>
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

<style scoped>
.user-info {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  box-shadow: 5px 5px 5px #888888;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#header {
  font-size: 1.75em;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-style: oblique;
  color: indigo;
}
</style>