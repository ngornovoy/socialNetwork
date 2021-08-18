<template>
     <v-list three-line>

        <v-list-item-group  class="pa-4">

          <user-link :user="comment.author">

          </user-link>

          <v-list-item-content  class="px-4">

               <v-list-item-subtitle>{{comment.text}}</v-list-item-subtitle>
          </v-list-item-content>

        </v-list-item-group>
    </v-list>
</template>

<script>
    import UserLink from 'components/UserLink.vue'
    export default {
        name: 'CommentItem',
        components: {UserLink},
        props: ['comment']
    }
</script>

<style scoped>
</style>