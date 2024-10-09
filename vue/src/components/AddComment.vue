<template>
  <div class="comment-container">
    <textarea 
        v-model="commentContent" 
        placeholder="Add a comment" 
        class="comment-textarea"
        :class="{ 'is-invalid': errorMessage }">
    </textarea>
    <div class="submit-button">
        <button class="button is-link" @click="submitComment" :disabled="isLoading">
            <span v-if="isLoading">Submitting...</span>
            <span v-else>Submit</span>
        </button>
    </div>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script>
import ForumService from '../services/ForumService';

export default {
    name: 'AddComment',
    props: {
        forumId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            commentContent: '',
            isLoading: false,
            errorMessage: ''
        }
    },
    methods: {
        async submitComment() {

            // Check if comment field is empty
            if (!this.commentContent.trim()) {
                this.errorMessage = 'Comment field cannot be empty.';
                return;
            }

            // Reset error message and start loading state
            this.errorMessage = '';
            this.isLoading = true;

            try {
                const newComment = {commentContent: this.commentContent, forumId: this.forumId};
                await ForumService.addCommentToForum(this.forumId, newComment);
                this.$emit('commentAdded');
                this.commentContent = '';
            } catch (error) {
                console.error('Error submitting comment', error);
                this.errorMessage = 'Failed to submit comment. Please try again.';
            } finally {

                // End the loading state
                this.isLoading = false;
            }
        }
    }
}
</script>

<style scoped>
.comment-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
}

.comment-textarea {
    width: 80%;
    height: 100px;
    margin-bottom: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    resize: vertical;
}

</style>