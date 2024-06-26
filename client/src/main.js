import { registerPlugins } from '@/plugins'
import './styles/dialog.scss';
import './styles/table.scss';


import App from './App.vue'

import { createApp } from 'vue'

const app = createApp(App)

registerPlugins(app)

app.mount('#app')
