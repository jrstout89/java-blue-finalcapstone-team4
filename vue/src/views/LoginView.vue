<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1 id="header" >Please Sign In</h1>
      <br>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit" class="button is-primary is-light">Sign in</button>
      <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#header{
  font-size: 3em;
  color: rgb(130, 215, 4);
  font-family:cursive;
  font-weight: bold;
}

.form-input-group {
  margin-bottom: 1rem;
  display: flex;
  justify-content: center;
  color: teal;
  font-size: 1.2em;
}
label {
  margin-right: 0.5rem;
}
#login{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50vh; 
  padding: 20px;
  border-radius: 5px;
  border: 1px solid black;
  border-radius: 10px;
  background-color: white;
  box-shadow: 5px 5px 5px #888888;
}
</style>