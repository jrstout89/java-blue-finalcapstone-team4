<template>
  <div class="forum-form">
    <h2>{{ isEditMode ? 'Edit Forum' : 'Add New Forum' }}</h2>
    <form @submit.prevent="submitForm">
        <div>
            <label for="forumTitle">Title: </label>
            <input type="text" v-model="forumTitle" required />
        </div>
        <div>
            <label for="'forumContent'">Content: </label>
            <textarea v-model="forumContent" required></textarea>
        </div>
        <button type="submit"> {{ isEditMode ? 'Update Forum' : 'Create Forum' }}</button>
        <button type="button" @click="cancel">Cancel</button>
    </form>
  </div>
</template>

<script>
import ForumService from '../services/ForumService';

export default {
    props: {
        forum: {
            type: Object,
            isEditMode: {
                type: Boolean,
                default: false
            }
        }
    },
    data() {
        return {
            forumTitle: this.forum ? this.forum.forumTitle : '',
            forumContent: this.forum ? this.forum.forumContent : ''
        }
    },
    methods: {
        submitForm() {
            const forumData = {
                forumTitle: this.forumTitle,
                forumContent: this.forumContent
            };

            if (this.isEditMode) {
                this.updateForum(this.forum.id, forumData).then(() =>
                this.$emit('refreshForumList'));
            } else {
                ForumService.addForum(forumData).then(() =>
                this.$emit('refreshForumList'));
            }
        },
        cancelSubmission() {
            this.$emit('cancel');
        }
    }
}
</script>