
<template>
        <v-main>
                <v-layout justify-space-around>
                        <v-flex :xs6="!$vuetify.breakpoint.xsOnly">
                                <div class="title mb-3">{{profile.name}}</div>
                                <v-layout row justify-space-between>
                                        <v-flex class="px-1">
                                                <v-img :src="profile.userpic"></v-img>
                                        </v-flex>
                                        <v-flex class="px-1">
                                                <v-layout column>

                                                        <v-flex>Язык: {{profile.locale}}</v-flex>
                                                        <v-flex>Пол: {{profile.gender}}</v-flex>
                                                        <v-flex>Email: {{profile.email}}</v-flex>

                                                        <v-flex>Последнее посещение: {{profile.lastVisit}}</v-flex>
                                                        <router-link
                                                            v-if="isMyProfile"
                                                            :to="`/subscribers/${profile.id}`"
                                                        >
                                                        {{profile.subscriptions && profile.subscriptions.length}} Подписки
                                                         </router-link>

                                                        <v-flex v-else>{{profile.subscriptions && profile.subscriptions.length}} Подписки </v-flex>
                                                        <router-link
                                                            v-if="isMyProfile"
                                                            :to="`/subscriptions/${profile.id}`"
                                                        >
                                                            {{profile.subscribers && profile.subscribers.length}} Подписчики
                                                        </router-link>
                                                        <v-flex v-else>{{profile.subscribers && profile.subscribers.length}} subscribers</v-flex>
                                                </v-layout>
                                                <v-btn
                                                    v-if="!isMyProfile"
                                                    @click="changeSubscription"
                                                >
                                                    {{isISubscribed ? 'Unsubscribe' : 'Subscribe' }}
                                                </v-btn>
                                        </v-flex>
                                </v-layout>
                        </v-flex>
                </v-layout>
        </v-main>
</template>

<script>
    import profileApi from 'api/profile'
    export default {
            name: 'Profile',
            data() {
                return {
                    profile: {}
                }
            },
            computed: {
                isMyProfile() {
                    return !this.$route.params.id ||
                        this.$route.params.id === this.$store.state.profile.id
                },
                isISubscribed() {
                    return this.profile.subscribers &&
                        this.profile.subscribers.find(subscription => {
                            return subscription.subscriber === this.$store.state.profile.id
                        })
                }
            },
            watch: {
                '$route'() {
                    this.updateProfile()
                }
            },
            methods: {
                async changeSubscription() {
                    const data = await profileApi.changeSubscription(this.profile.id)
                    this.profile = await data.json()

                },
                async updateProfile() {
                    const id = this.$route.params.id || this.$store.state.profile.id
                    const data = await profileApi.get(id)
                    this.profile = await data.json()
                    this.$forceUpdate()
                }
            },
            beforeMount() {
                this.updateProfile()
            }
    }
</script>

<style scoped>
    img {
        max-width: 100%;
        height: auto;
    }
</style>