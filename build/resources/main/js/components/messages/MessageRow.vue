<template>
    <span row align-center  class="spacing-playground pa-5">
    <v-card class="my-1" >
        <v-card-text primary-title>
            <user-link
                :user ="message.author"
                :size="46">
            </user-link>
            <div class="spacing-playground pa-5">
                <span class="black--text"> {{ message.text }}</span>

            </div>
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
        <v-divider></v-divider>
        <v-card-actions v-if="message.author.id === this.$store.state.profile.id">
            <v-btn value="Edit" @click="edit" small text rounded     >Edit</v-btn>
            <v-btn icon @click="del" small>
                <v-icon>{{ trash }}</v-icon>
            </v-btn>
        </v-card-actions>
        <v-divider></v-divider>
        <comment-list
            :comments="this.message.comments"
            :messageId="this.message.id"
        >

        </comment-list>
    </v-card>
    </span>
</template>





<script>

    import { mapActions } from 'vuex'
    import Media from 'components/media/Media.vue'
    import UserLink from 'components/UserLink.vue'

    import CommentList from '../comment/CommentList.vue'
      import { mdiDelete } from '@mdi/js'
    export default {
        props: ['message', 'editMessage'],
        data(){
            return{
                trash: mdiDelete
            }
        },
        components: { CommentList, Media, UserLink },

        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {

                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            },

        },

    }
</script>

<style>
</style>
