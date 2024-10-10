<template>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1 id="header">Create Account</h1>
      <br>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" v-model="user.firstName" required/>
      </div>
      <div class="form-input-group">
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" v-model="user.LastName" required/>
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <div class="form-input-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="user.email" required/>
      </div>
      <div class="form-input-group">
        <label for="phoneNumber">Phone Number</label>
        <input type="text" id="phoneNumber" v-model="user.phoneNumber"/>
      </div>
      <button type="submit" class="button is-primary is-light">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: '', 
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
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
#register {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 75vh; 
  padding: 20px;
  border-radius: 5px;
  border: 1px solid black;
  border-radius: 10px;
  background-color: white;
  box-shadow: 5px 5px 5px #888888;
}
#header {
  font-size: 3em;
  color: rgb(130, 215, 4);
  font-family:cursive;
  font-weight: bold;
}
</style>
