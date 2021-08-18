<template>
    <v-layout  row align-center  class="pa-4">

        <v-list-item-group  class="pa-4">

        <div class="title mb-3">Ваши подписки:</div>

        <v-list-item-content  class="pa-4"
            v-for="item in subscribers"
        >
            <user-link :user="item.channel" size="">

            </user-link>

        </v-list-item-content>

        </v-list-item-group>
    </v-layout>

</template>

<script>
    import profileApi from 'api/profile'
    import UserLink from 'components/UserLink.vue'
    export default{
        name: 'Subscribers',
        components: {UserLink},
        data() {
            return {
                subscribers : []
            }
        },

            async beforeMount() {
                const resp = await profileApi.subscriptionsList(this.$store.state.profile.id)
                console.log(resp)
                this.subscribers = await resp.json()
            }
    }

</script>

<style scoped>

</style>