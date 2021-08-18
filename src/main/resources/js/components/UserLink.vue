<template>
    <router-link
        :to="`/user/${user.id}`"
    >
        <v-avatar
                v-if="user && user.userpic"
                :size="`${size || 35}px`"
                class="mr-3"
        >
            <img
                    :src="user.userpic"
                    :alt="user.name"
            >
        </v-avatar>

        <v-avatar
                v-else
                :size="`${size || 35}px`"
                color="indigo"
                class="mr-3"
        >
            <v-icon dark>account_circle</v-icon>
        </v-avatar>
        <span>{{ user.name }}</span>
     </router-link>
</template>

<script>
    export default{
        name: 'UserLink',
        props: ['user', 'size'],
        computed: {

           userName() {
               return this.user ? this.user : 'unknown'
           },

       },
    }
</script>

<style scoped>
</style>