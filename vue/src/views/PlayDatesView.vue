<template>
    <div class="content-container">
        <div class="left-column">
             <!-- Filter will go here -->
        </div>
        <location></location>
        <current-events></current-events>
    </div>
    <!-- </div> -->

</template>

<script>
import CurrentEvents from '../components/CurrentEvents.vue'
import location from '../components/Location.vue';

export default {
    components:{
        CurrentEvents,
        location
    },
    

}
</script>

<style>
.content-container {
    display: flex;
}

.left-column {
    width: 20%; 
    float: left;
    height: 100vh; /* This will make it take the full height of the viewport */
    background-color: #f0f0f0;
    padding: 1em;
    box-sizing: border-box; /* This makes the padding included in the element's total width and height */
}
</style>