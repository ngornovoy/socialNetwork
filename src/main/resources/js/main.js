import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'

import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store'
import { connect } from './util/ws'
import 'vuetify/dist/vuetify.min.css'
import * as Sentry from "@sentry/vue"
import { Integrations } from "@sentry/tracing"

Sentry.init({
  Vue,
  dsn: "https://a20b398ce1d34c348a8dac5b5eaaf9ba@o760282.ingest.sentry.io/5792857",
  integrations: [new Integrations.BrowserTracing()],


  tracesSampleRate: 1.0,
});
Sentry.configureScope(scope => scope.setUser({
    id: profile && profile.id,
    username: profile && profile.name
}))
if (profile) {
    connect()
}

Vue.use(Vuetify,{ iconfont: 'mdiSvg' });


new Vue({
    el: '#app',
    store,
    router,
    vuetify: new Vuetify(),
    render: a => a(App)
})
