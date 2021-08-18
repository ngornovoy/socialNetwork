<template>
    <v-layout  row align-center  class="pa-4">

        <v-list-item-group  class="pa-4">

         <div class="title mb-3">Ваши подписчики:</div>

        <v-list-item-content  class="pa-4"
            v-for="item in subscriptions"
        >
            <user-link :user="item.subscriber" size="">

            </user-link>
            <v-btn
                @click="changeSubscriptionStatus(item.subscriber.id)"
            >
                {{item.active ? "Dismiss" : "Approve"}}
            </v-btn>
        </v-list-item-content>

        </v-list-item-group>
    </v-layout>

</template>

<script>
    import profileApi from 'api/profile'
    import UserLink from 'components/UserLink.vue'
    export default{
        name: 'Subscriptions',
        components: {UserLink},
        data() {
            return {
                subscriptions: []
            }
        },
         methods: {
            async changeSubscriptionStatus(subscriberId) {
                await profileApi.changeSubscriptionStatus(subscriberId)
                const subscriptionIndex = this.subscriptions.findIndex(item =>
                    item.subscriber.id === subscriberId
                )
                const subscription = this.subscriptions[subscriptionIndex]
                this.subscriptions = [
                    ...this.subscriptions.slice(0, subscriptionIndex),
                    {
                        ...subscription,
                        active: !subscription.active
                    },
                    ...this.subscriptions.slice(subscriptionIndex + 1)
                ]
            }
         },
            async beforeMount() {
                const resp = await profileApi.subscriberList(this.$store.state.profile.id)
                this.subscriptions = await resp.json()
            }
    }

</script>

<style scoped>

</style>