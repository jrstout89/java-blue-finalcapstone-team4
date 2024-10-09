import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import PlayDatesView from '../views/PlayDatesView.vue';
import UserView from '../views/UserView.vue';
import PetView from '../views/PetView.vue';
import AddPetView from '../views/AddPetView.vue';
import ForumListView from '../views/ForumListView.vue';
import ForumDetailView from '../views/ForumDetailView.vue';
import PetDetails from '../views/PetDetailsView.vue';
import EventDetails from '../components/EventDetails.vue';
import AddEventView from '../views/AddEventView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/playdates",
    name: "playDates",
    component: PlayDatesView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/user",
    name: "user",
    component: UserView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/:customerId/pets",
    name: "pets",
    component: PetView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/pets/add-pet",
    name: "addPet",
    component: AddPetView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/pets/update-pet/:id",
    name: "updatePet",
    component: AddPetView,
    meta: { requiresAuth: true }
  },
  {
    path: "/forum",
    name: "forum",
    component: ForumListView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/forum/:id",
    name: "forumDetails",
    component: ForumDetailView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/pets/:id",
    name: "petDetails",
    component: PetDetails,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/playdates/:id",
    name: "eventDetails",
    component: EventDetails,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/events/add-event/",
    name: "addEvent",
    component: AddEventView,
    meta: {requiresAuth: true}
  },
  {
    path: "/events/update-event/:id",
    name: "updateEvent",
    component: AddEventView,
    meta: { requiresAuth: true }
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
