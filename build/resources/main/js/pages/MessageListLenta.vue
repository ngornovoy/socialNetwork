<template>
    <v-main>
        <v-layout align-space-around justify-start column>
            <message-form  :messageAttr="message" />
            <message-row v-for="message in sortedMessages"
                     :key="message.id"
                     :message="message"
                     :editMessage="editMessage"
                     />
            <lazy-loader :type=false></lazy-loader>
        </v-layout>
    </v-main>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import LazyLoader from 'components/LazyLoader.vue'
    import { mapState,  mapGetters } from 'vuex'
    import { mapActions } from 'vuex'

    export default{

        components: {
            MessageRow,
            MessageForm,
            LazyLoader
         },
         data(){
           return {
               message: null
           }
         },
         computed: mapGetters(['sortedMessages']),
         mounted(){
            this.loadLenta()
         },

         methods: {
           ...mapActions(['loadLenta']),
           editMessage(message){
               this.message = message
           },

         }
    }
</script>

<style>

</style>