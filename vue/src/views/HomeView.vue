<template>
    <nav class="about">
      <div id="content">
        <img id="dog" src="../img/HappyDog.gif" alt="happy" />
        <h3 id="subtitle">Welcome to Pet Play Pal!</h3>
      </div>
        <p id="text">At Pet Play Pals, we provide a secure and enjoyable space where pets can interact and form friendships. Our dedicated team focuses on enriching activities designed to meet each pet’s unique needs. Join us in cultivating a joyful community for all furry companions!
        </p>
        <!-- <router-link :to="{ name: 'pets', params: { customerId: $store.state.user.id } }">Pets</router-link> -->
        <button>View Play Dates</button>
        <div class="slideshow">
          <img src="images[currentImageIndex]" alt="Slideshow Image" class="slide" />
          <div class="image-index">
            {{ currentImageIndex + 1 }} / {{ images.length }}
          </div>
          <button @click="prevImage">Previous</button>
          <button @click="nextImage">Next</button>
        </div>
    </nav>
    <footer>
      <p>&copy; 2024 Pet Play Pals. All rights reserved.</p>
    </footer>
</template>

<script>

export default {
  data() {
    return {
      images: [
        'https://media.istockphoto.com/id/1341759749/photo/happy-and-energetic-golden-retriever-playing-chase-with-owner.jpg?s=612x612&w=0&k=20&c=dz2JoJpE96woGAvC76c1dsqAnbniVwMJVnqp4qJ-yMc=',
        'https://www.animalbehaviorcollege.com/wp-content/uploads/2019/09/dog-park-1-768x513.jpg',
      ],
      currentImageIndex: 0,
      isVisible: false
    };
  },
  methods: {
    nextImage() {
      this.currentImageIndex = (this.currentImageIndex + 1) % this.images.length;
    },
    previousImage() {
      this.currentImageIndex = (this.currentImageIndex - 1 + this.images.length) % this.images.length;
    },
    toggleVisibility() {
      this.isVisible = !this.isVisible;
    }
  },
};
</script>

<style>
.header-container {
    display: flex; 
    align-items: center;
    justify-content: space-between; 
    padding: 5px; 
    border-bottom: 1px solid black;
  }
.tabs{
  margin-left: auto;
  }
#greeting{
    font-size: 3em;
    margin-bottom: 20px;
    font-family: cursive;
  }

.about {
  display: flex;
    flex-direction: column;
    align-items: center; 
    justify-content: center;
    background-image: url('../img/running.jpg' );
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    height: 100vh;
    width: auto;
  }
#content{
    display: flex;
    flex-direction: row;
    width: 100%;
    justify-content: center;
  }
#subtitle{
    font-size: 2em;
    font-family: cursive;
  }
  #dog{
    width: 50px;
    margin-right: 10px;
    height: auto;
  }
  #text{
    font-size: 1.5em;
    font-family: cursive;
  }

  .slideshow {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
  }

  .slide {
    width: 100%;
    height: 600px;
    margin-bottom: 10px;
  }

  button {
    margin: 5px;
    padding: 10px;
  }

  .image-index {
    margin: 10px 0;
    font-size: 1.2rem;
  }

</style>
    
    

