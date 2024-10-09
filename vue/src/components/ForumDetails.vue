<template>
  <div class="forum-info">
    <h2>{{ forum.forumTitle }}</h2>
    <p style="display: inline">Posted by: {{ forum.username }}</p>
    <p style="display: inline; margin-left: 5px;">on: {{ formatDate(forum.createdDate) }}</p>
    <p>{{ forum.forumContent }}</p>
    <div class="button-container">
      <button class="button is-success" @click="editForum">Edit Forum</button>
      <button class="button is-warning" @click="deleteForum">Delete Forum</button>
    </div>
    <div v-if="isEditing" class="edit-container">
      <input v-model="updatedForumTitle" placeholder="Forum Title" class="input-field" />
      <textarea v-model="updatedForumContent" placeholder="Forum Content" class="textarea-field"></textarea>
      <div class="button-container">
        <button class="button is-info" @click="updateForum">Save Changes</button>
        <button class="button is-warning" @click="cancelEdit">Cancel</button>
      </div>
    </div>
  </div>
</template>

<script>
import ForumService from '../services/ForumService';

export default {
  name: 'ForumDetails',
  props: {
    forum: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      isEditing: false,
      updatedForumTitle: this.forum.forumTitle,
      updatedForumContent: this.forum.forumContent
    }
  },
  methods: {
    editForum() {
      this.isEditing = true;
    },
    cancelEdit() {
      this.isEditing = false;
      this.updatedForumTitle = this.forum.forumTitle;
      this.updatedForumContent = this.forum.forumContent;
    },
    async updateForum() {
      console.log('Updating forum ID:', this.forum.id);
      const updatedForum = {
        customerId: this.forum.customerId,
        forumTitle: this.updatedForumTitle,
        forumContent: this.updatedForumContent,
        updateDate: new Date().toISOString().split('T')[0]
      };
      try {
        await ForumService.updateForum(this.forum.id, updatedForum);
        this.$emit('forum-updated', {id: this.forum.id, updatedForum});
        this.isEditing = false;
      } catch (error) {
        console.error('Error updating forum', error);
      }
    },
    async deleteForum() {
      const confirmDelete = confirm('Are you sure you want to delete this forum?');
      if (confirmDelete) {
        try {
          await ForumService.deleteForum(this.forum.id);
          this.$emit('forum-deleted', this.forum.id);
          this.$router.push({ name: 'forum' });
        } catch (error) {
          console.error('Error deleting forum', error);
        }
      }
    },
    formatDate(date) {
      const newDate = new Date(date);
      const options = { month: '2-digit', day: '2-digit', year: 'numeric' };
      return newDate.toLocaleDateString('en-US', options);
    }
  }
}
</script>

<style>
.forum-info {
  background-color: #f9f9f9;
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.forum-info h2 {
    font-size: 2.5rem;
    margin-bottom: 10px;
    color: #333;
  }

.edit-container {
  margin-top: 20px;
}

.input-field, .textarea-field {
  width: 85%;
  margin-bottom: 10px;
}

.textarea-field {
  height: 100px;
}

.button-container {
  display: flex;
  justify-content: center;
  gap: 10px;
}

</style>