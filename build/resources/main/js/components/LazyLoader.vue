<template>
    <span></span>
</template>

<script>
    import { mapActions } from 'vuex'
    export default {
        name: 'LazyLoader',
        props: ['type'],
        methods: {
                ...mapActions(['loadPageActionLenta']),
                ...mapActions(['loadPageAction'])
        },


        mounted() {
            window.onscroll = () => {
                const el = document.documentElement
                const isBottomOfScreen = (window.innerHeight + window.pageYOffset) >= document.body.offsetHeight - 2

                if (isBottomOfScreen) {
                    if(this.type === true) {
                        this.loadPageAction()

                    }
                    else {
                        this.loadPageActionLenta()

                    }
                }
            }
        },
        beforeDestroy() {
            window.onscroll = null
        }
    }
</script>

<style scoped>
</style>