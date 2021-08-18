<template>
    <v-app >
        <v-app-bar app>

                <v-app-bar-title class="ma-4"> ВКР </v-app-bar-title>
                <v-btn text
                       v-if="profile"
                       :disabled="$route.path === '/'"
                       @click="showMessages">
                    Подписки
                </v-btn>
                <v-btn text
                       v-if="profile"
                       :disabled="$route.path === '/lenta'"
                       @click="showMessagesLenta">
                    Лента
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn text
                       v-if="profile"
                       :disabled="$route.path === '/user'"
                       @click="showProfile">
                    {{profile.name}}
                </v-btn>
                <v-btn v-if="profile" icon href="/logout">
                    <v-icon>{{logout}}</v-icon>
                </v-btn>
        </v-app-bar>
        <v-main>
            <router-view></router-view>
        </v-main>

    </v-app>
</template>

<script>
     import { mapState, mapMutations, mapActions } from 'vuex'
     import { addHandler } from 'util/ws'
     import { mdiExitToApp } from '@mdi/js'
     import messagesApi from 'api/messages'
     export default {
         computed: mapState(['profile']),
         methods: {
             ...mapMutations([
                 'addMessageMutation',
                 'updateMessageMutation',
                 'removeMessageMutation',
                 'addCommentMutation'
             ]),

             ...mapActions(['checkSubIsActive']),
             showMessages() {

                 this.$router.push('/')
             },
             showMessagesLenta() {

                this.$router.push('/lenta')
             },
             showProfile() {
                 this.$router.push('/user')
             }
         },
         created() {
             addHandler(data => {
                 if (data.objectType === 'MESSAGE') {
                     switch (data.eventType) {
                         case 'CREATE':
                             if(this.$store.state.where){
                                this.addMessageMutation(data.body)
                             }else{

                                for (var i = 0; i < profile.subscriptions.length; i++) {

                                    if ( profile.subscriptions[i].active === true){

                                        if(profile.subscriptions[i].channel.id == data.body.author.id) {

                                             this.addMessageMutation(data.body)
                                        }
                                    }
                                }
                             }
                             break
                         case 'UPDATE':
                             this.updateMessageMutation(data.body)
                             break
                         case 'REMOVE':
                             this.removeMessageMutation(data.body)
                             break
                         default:
                             console.error(`Looks like the event type if unknown "${data.eventType}"`)
                     }
                 } else if (data.objectType === 'COMMENT') {
                     switch (data.eventType) {
                         case 'CREATE':
                             this.addCommentMutation(data.body)
                             break
                         default:
                             console.error(`Looks like the event type if unknown "${data.eventType}"`)
                     }
                 } else {
                     console.error(`Looks like the object type if unknown "${data.objectType}"`)
                 }
             })
         },
         data() {
             return {
                 logout: mdiExitToApp
             }
         },
         beforeMount() {
             if (!this.profile) {
                 this.$router.replace('/auth')
             }
         }
     }
 </script>

 <style>
 </style>