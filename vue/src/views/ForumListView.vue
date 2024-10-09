<template>
    <div class="forum-header">
        <div class="intro-bubble">
            <h1>Welcome to the Pet Play Pals forum!!</h1>
            <p>This is a friendly space for pet lovers to share stories, ask questions, and connect with fellow enthusiasts. Whether you're seeking advice on pet care, looking to share your experiences, or just want to chat about your furry friends, you're in the right place! Join the conversation and help us create a vibrant community dedicated to all things pets.</p>
        </div>
        <button class="button is-info" @click="toggleAddForumButton">
            {{ showAddForumForm ? 'Cancel' : 'Add Forum' }}
        </button>
        <div v-if="showAddForumForm">
            <input v-model="newForumTitle" placeholder="Forum Title" />
            <textarea v-model="newForumContent" placeholder="Forum Content"></textarea>
            <button class="button is-info" @click="addForum">Submit</button>
        </div>
        <ul class="forum-list">
            <li class="forum-bubble" v-for="forum in forums" :key="forum.id">
                <router-link :to="{ name: 'forumDetails', params: { id: forum.id } }">
                    <h2>{{ forum.forumTitle }}</h2>
                </router-link>
                <p style="display: inline;">Posted by: {{ forum.username }}</p>
                <p style="display: inline; margin-left: 5px;">on {{ formatDate(forum.createdDate) }}</p>
            </li>
        </ul>
    </div>
</template>

<script>
import ForumService from '../services/ForumService';

export default {
    data() {
        return {
            forums: [],
            showAddForumForm: false,
            newForumTitle: '',
            newForumContent: ''
        }
    },
    created() {
        this.getForums();
    },
    methods: {
        getForums() {
            ForumService.getForums().then(response => {
                this.forums = response.data;
            }).catch(error => {
                console.error('Error getting forums', error);
            });
        },
        toggleAddForumButton() {
            this.showAddForumForm = !this.showAddForumForm;
        },
        addForum() {
            const newForum = {
                forumTitle: this.newForumTitle,
                forumContent: this.newForumContent
            };

            ForumService.addForum(newForum).then(() => {
                this.getForums();
                this.showAddForumForm = false;
                this.newForumTitle = '';
                this.newForumContent = '';
            }).catch(error => {
                console.error('Error adding forum', error);
                console.log('Error adding forum:', error.response ? error.response.data : error.message);
            });
        },
        formatDate(date) {
            const newDate = new Date(date);
            const options = { month: '2-digit', day: '2-digit', year: 'numeric' };
            return newDate.toLocaleDateString('en-US', options);
        }
    }
}
</script>

<style scoped>
.forum-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px 0;
}

.forum-bubble {
    background-color: #f1f1f1;
    border: 1px solid #ddd;
    border-radius: 12px;
    padding: 15px 20px;
    margin: 10px 0;
    width: 95%;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    text-align: left;
}

.forum-bubble h2 {
    font-size: 1.5rem;
    margin: 0;
}

.forum-bubble p {
    margin: 5px 0;
    color: #666;
}

.intro-bubble {
    background-color: #f9f9f9;
    border: 2px solid #ccc;
    border-radius: 15px;
    padding: 20px;
    margin: 20px auto;
    max-width: 98%;
    text-align: center;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.intro-bubble h1 {
    font-size: 2.5rem;
    margin-bottom: 10px;
    color: #333;
}

.intro-bubble p {
    font-size: 1.2rem;
    line-height: 1.5;
    color: #666;
}

</style>