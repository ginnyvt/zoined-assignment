import { createWebHistory, createRouter } from 'vue-router';

import UploadFile from './components/zoined/UploadFile.vue';
import Dashboard from './components/zoined/Dashboard.vue';

const routes = [
	{ path: '/upload', name: 'UploadFile', component: UploadFile },
	{ path: '/dashboard', name: 'Dashboard', component: Dashboard },
	{ path: '/:pathMatch(.*)*', redirect: '/upload' },
];

const router = createRouter({
	history: createWebHistory(),
	routes: routes,
});

export default router;
