<template>
  <div>  
    <div id="grid-container">
      <!-- Google Maps will render map here -->
      <div id="map"></div>
    </div>
  </div>
  </template>

<script>
import eventService from '../services/eventService';

export default {
name: "Map",
data() {
return {
  map: null,
  routeService : null,
  routeRendererService: null,
  currentInput : "",
  roundTrip : true,
  mapCenter: { lat: 42.35866, lng:  -71.05674 },
  event: {}
};
},

methods: {
// This function is called during load, but can also be called to reset the map
initMap() {

  this.map = new window.google.maps.Map(document.getElementById("map"), {
    center: this.mapCenter,
    zoom: 13,
    maxZoom: 20,
    minZoom: 3,
    streetViewControl: true,
    mapTypeControl: true,
    fullscreenControl: true,
    zoomControl: true,
  });
  let noPOIStyle = [
    {
      featureType: "poi",
      elementType: "labels",
      stylers: [{ visibility: "off" }],
    },
  ];
  this.map.setOptions({ styles: noPOIStyle });


  eventService.getEvents().then((response) => {
  this.events = response.data;
  this.events.forEach(event => {
      let coordinates = {
      lat: parseFloat(event.latitude),
      lng: parseFloat(event.longitude)
      };

  let marker = new window.google.maps.Marker({
    position: coordinates,
    map: this.map,
    title:"Location: " + event.eventLocation + "\n" + "Event Name: " + event.eventTitle,
    clickable: true
  });
  marker.addListener("click", () => {
  this.$router.push({ name: 'eventDetails', params: { id: event.id } });
});
});
});
},
},
mounted() {
this.initMap();
},
};
</script>

<style scoped>

/* #grid-container {
display: grid;
grid-template-columns: 1fr 1fr;
grid-template-areas: 
"map map";
} */
#grid-container {
display: flex;
justify-content: center;
align-items: center;
}

#map {
grid-area: map;
width: 500px;
height: 750px;
padding: 25px;
}

/* #filter {
    width: 20%; 
    float: left;
    height: 100vh; /* This will make it take the full height of the viewport */
    /* background-color: #f0f0f0;
    padding: 1em;
    box-sizing: border-box; /* This makes the padding included in the element's total width and height */
    /* display: grid;
    grid-area: "filter"; */
</style>