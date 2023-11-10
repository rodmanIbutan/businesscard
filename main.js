import App from './App'

// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
	...App
})
app.$mount()
// #endif

// #ifdef VUE3
import {
	createSSRApp
} from 'vue'
export function createApp() {
	const app = createSSRApp(App)
	return {
		app
	}
}
// #endif

Vue.prototype.$http = "https://ecard.wzpm.com.cn:8090/businesscard/"
// Vue.prototype.$http = "https://localhost:8081/businesscard/"
Vue.prototype.$company = ["五洲工程顾问集团有限公司", "杭州千城建筑设计集团股份有限公司"]
Vue.prototype.$publicAddress = {
	wuzhou: ['杭州市滨江区东信大道688号志成大厦13楼', '杭州市滨江区浦沿街道六和路307号六和大厦E幢15楼', '杭州市滨江区浦沿街道六和路307号六和大厦E幢18楼',
		'杭州市滨江区浦沿街道六和路307号六和大厦E幢21楼'
	],
	qiancheng: ['杭州市滨江区浦沿街道六和路307号六和大厦E幢20楼', '杭州市滨江区浦沿街道六和路307号六和大厦E幢21楼']
}