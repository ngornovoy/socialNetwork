<template>

    <v-layout align-center  class="pa-4">

            <v-text-field
                    label="Add comment"
                    placeholder="Write something"
                    v-model="text"
                    @keyup.enter="save"
            />
            <v-btn class="ma-2"
                   outlined
                   color="indigo"
                   align="center"
                   @click="save">
                Add
            </v-btn>

    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'
    export default {
        name: 'CommentForm',
        props: ['messageId'],
        data() {
            return {
                text: ''
            }
        },
        methods: {
            ...mapActions(['addCommentAction']),
            async save() {

                await this.addCommentAction({
                    text: this.text,
                    message: {
                        id: this.messageId
                    }
                })
                this.text = ''
            }
        }
    }
</script>

<style scoped>
</style>