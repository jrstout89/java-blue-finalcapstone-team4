<template>
  <div>
    <ForumDetails :forum="forum" v-if="forum.id" />
    <CommentsList :comments="comments" />
    <AddComment v-if="forum.id" :forumId="forum.id" @commentAdded="fetchComments" />
    <div v-if="error">{{ error }}</div>
    <div v-if="loading">Loading...</div>
  </div>
</template>

<script>
import ForumService from '../services/ForumService';
import ForumDetails from '../components/ForumDetails.vue';
import CommentsList from '../components/CommentsList.vue';
import AddComment from '../components/AddComment.vue';

export default {
  components: {
    ForumDetails,
    CommentsList,
    AddComment
  },
  data() {
    return {
      forumId: this.$route.params.id,
      forum: {},
      comments: [],
      loading: true,
      error: null
    }
  },
  created() {
    this.getForumDetails();
    // this.forumId = this.$route.params.id;
    // console.log('Forum ID from route: ', this.forumId);
    // this.getForumDetails();
    // this.getComments();
  },
  watch: {
    '$route.params.id': async function() {
      await this.getForumDetails();
      await this.getComments();
    }
  },
  methods: {
    // async getForumDetails() {
    //   this.forumId = this.$route.params.id;
    //   console.log('Forum ID from route: ', this.forumId);
    //   try {
    //     const response = await ForumService.getForumById(this.forumId);
    //     this.forum = response.data;
    //     console.log('Forum ID before getting comments: ', this.forumId);
    //     await this.getComments();
    //   } catch (error) {
    //     console.error('Error getting forum details', error);
    //     // console.log('Forum ID: ', this.forum.id);
    //     // console.log('Auth Token: ', localStorage.getItem('token'));
    //   }
    // },
    // async getComments() {
    //   try {
    //     const response = await ForumService.getCommentsForForum(this.forum.id);
    //     console.log('Comments Response: ', response.data);
    //     this.comments = response.data;
    //   } catch (error) {
    //   console.error('Error getting comments: ', error.response ? error.response.data : error.message);
    //   // console.log('Forum ID: ', this.forum.id);
    //   // console.log('Auth Token: ', localStorage.getItem('token'));
    //   }
    // },
    async getForumDetails() {
      this.loading = true;
      this.error = null;
      try {
        const response = await ForumService.getForumById(this.forumId);
        this.forum = response.data;
        await this.getComments();
      } catch (error) {
        console.error('Error getting forum data', error);
        this.error = 'Error getting forum details: ' + (error.response ? error.response.data : error.message);
      } finally {
        this.loading = false;
      }
    },
    async getComments() {
      this.error = null;
      if (!this.forum.id) return;
      try {
        const response = await ForumService.getCommentsForForum(this.forum.id);
        this.comments = response.data.reverse();
      } catch (error) {
        console.error('Error getting comments', error);
        this.error = 'Error getting comments: ' + (error.response ? error.response.data : error.message);
      }
    },
    fetchComments() {
      this.getComments();
    }
  }
}
</script>

<style>

</style>