<template>
    <v-list two-line>
        <v-subheader >
            Comments
        </v-subheader>

        <template
                v-for="(item, index) in this.comments" v-if="index < countCom"
        >
            <comment-item
                    :comment="item"
                    :key="'item' + index"
            ></comment-item>
            <v-divider></v-divider>

        </template>
        <v-col>
            <v-row>

                 <v-btn class="ma-3"
                      v-if="this.comments.length > 2 && this.comments.length > countCom"
                      outlined
                      color="indigo"
                      align="center"
                      @click="addCom"
                 >
                   More comments
                 </v-btn>

            </v-row>
            <v-row>
                <comment-form :messageId="this.messageId"></comment-form>
            </v-row>
            </v-col>
    </v-list>
</template>

<script>

    import CommentForm from './CommentForm.vue'
    import CommentItem from './CommentItem.vue'
    export default {
        data() { return {
            countCom: 2,

        }},
        name: 'CommentList',
        components: {CommentForm, CommentItem},
        props: ['comments', 'messageId'],
        methods: {

            addCom() {
                this.countCom++
                this.countCom++
            },

        }
    }


</script>

<style scoped>
</style>