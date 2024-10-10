<template>
    <nav class="about">
      <div class="info-bubble">
        <div id="content">
          <img id="dog" src="../img/HappyDog.gif" alt="happy" />
          <h3 id="subtitle">Welcome to Pet Play Pals!</h3>
        </div>
        <p id="text">At Pet Play Pals, we provide a secure and enjoyable space where pets can interact and form friendships. Our dedicated team focuses on enriching activities designed to meet each pet’s unique needs. Join us in cultivating a joyful community for all furry companions!
        </p>
      </div>
        <!-- <router-link :to="{ name: 'pets', params: { customerId: $store.state.user.id } }">Pets</router-link> -->
        <button class="button is-info">View Play Dates</button>
        <div class="slideshow">
          <div class="image-container">
            <img :src="images[currentImageIndex]" alt="Slideshow Image" class="slide" />
          </div>
          <!-- <div class="image-index">
            {{ currentImageIndex + 1 }} / {{ images.length }}
          </div> -->
          <!-- <button @click="prevImage">Previous</button>
          <button @click="nextImage">Next</button> -->
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
        'https://www.nappanee.in.gov/home/showpublishedimage/362/637907306438070000',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Tompkins_Square_Big_Dog_Run.JPG/1200px-Tompkins_Square_Big_Dog_Run.JPG',
        'https://rootedinfun.com/ImageRepository/Document?documentID=134',
        'https://library.playlsi.com/transform/620f85e4-f583-42b1-9df4-bc7d35c6d526/TX-Elyson-Commons-at-Bear-Creek-10?io=transform:extend,width:1920,height:1080,background:f9f9f9',
        'https://media-cldnry.s-nbcnews.com/image/upload/t_fit-1240w,f_auto,q_auto:best/newscms/2019_17/2836796/190426-dog-park-ew-251p.jpg',
        'https://www.doodycalls.com/cms/thumbnails/24/1060x560/sub/71074/images/wheaton.0000000000000.jpg',
        'https://s3-us-west-2.amazonaws.com/akccontentimages/AKC_Training_Microsite/101723373_sealyham_terrier_golden_retriever.jpg',
        'https://www.dundeeanimalhospital.com/images/blogs/ezgif.com-gif-maker.19).jpg',
      ],
      currentImageIndex: 0,
      isVisible: false,
      slideshowInterval: null,
    };
  },
  methods: {
    nextImage() {
      this.currentImageIndex = (this.currentImageIndex + 1) % this.images.length;
    },
    previousImage() {
      this.currentImageIndex = (this.currentImageIndex - 1 + this.images.length) % this.images.length;
    },
    startSlideshow() {
      this.slideshowInterval = setInterval(this.nextImage, 4000);
    },
    stopSlideshow() {
      clearInterval(this.slideshowInterval);
    },
    toggleVisibility() {
      this.isVisible = !this.isVisible;
    }
  },
  mounted() {
    this.startSlideshow();
  },
  beforeUnmount() {
    this.stopSlideshow();
  },
};
</script>

<style scoped>
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
    /* background-image: url('../img/running.jpg' ); */
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

  .image-container {
    border: 2px solid #ccc;
    border-radius: 8px;
    padding: 2px;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 800px;
    height: 600px;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
  }

  .slide {
    width: 100%;
    height: 100%;
    max-height: 100%;
    object-fit: cover;
    margin: 0;
  }

  button {
    margin: 5px;
    padding: 10px;
    margin-bottom: -10px;
  }

  .image-index {
    margin: 10px 0;
    font-size: 1.2rem;
  }

  .info-bubble {
    background-color: #f9f9f9;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin: -70px auto 10px;
    width: 1800px;
  }
  
</style>