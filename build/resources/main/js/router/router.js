
import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'pages/MessageList.vue'
import MessagesListLenta from 'pages/MessageListLenta.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import Subscriptions from 'pages/Subscriptions.vue'
import Subscribers from 'pages/Subscribers  .vue'
Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessagesList },
    { path: '/lenta', component: MessagesListLenta },
    { path: '/auth', component: Auth },
    { path: '/user/:id?', component: Profile },
    { path: '/subscriptions/:id', component: Subscriptions },
    { path: '/subscribers/:id', component: Subscribers },
    { path: '*', component: MessagesList },
]

export default new VueRouter({
    mode: 'history',
    routes
})