<template>
  <div>
    <ForumDetails 
    :forum="forum" 
    v-if="forum.id"
    @forum-updated="updateForum"
    @forum-deleted="deleteForum"
    />
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
  },
  watch: {
    '$route.params.id': async function() {
      await this.getForumDetails();
      await this.getComments();
    }
  },
  methods: {
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
    },
    async updateForum({ id, updatedForum }) {
      try {
        await ForumService.updateForum(id, updatedForum);
        this.forum.forumTitle = updatedForum.forumTitle;
        this.forum.forumContent = updatedForum.forumContent;
      } catch (error) {
        console.error('Error updating forum', error);
        this.error = 'Error updating forum: ' + (error.response ? error.response.data : error.message);
      }
    },
    async deleteForum(id) {
      try {
        await ForumService.deleteForum(id);
        this.$router.push({ name: 'forumList' });
      } catch (error) {
        console.error('Error deleting forum', error);
        this.error = 'Error deleting forum: ' + (error.response ? error.response.data : error.message);
      }
    }
  }
}
</script>

<style>

</style>