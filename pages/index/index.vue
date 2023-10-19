<template>
	<view id="app">
		<view v-if="islogin">
			<view class="title">我的名片</view>
			<view v-for="item in cardList" :key="item.id">
				<uni-card :title="item.title" :extra="company[cardList.company]" @click="goto(item.id)">
					<text class="uni-body">{{ item.remarks }}</text>
				</uni-card>
			</view>
			<view class="add" @click="add()">
				+
			</view>
		</view>
		<view v-else>
			<view class="loginre">
				请先登录!
			</view>
			<button @click="tologin()">登录</button>
		</view>
	</view>
</template>

<script>
	import Card from "./component/card.vue"
	export default {
		components: {
			Card
		},
		data() {
			return {
				islogin: false,
				cardList: [],
				company:["五洲","千城"]
			}
		},
		onReady() {
			if (!uni.getStorageSync("token")) {
				uni.reLaunch({
					url: '/pages/login/login'
				})
			} else {
				this.islogin = true
				uni.request({
					url: this.$http + "card/getListById",
					header: {
						'token': uni.getStorageSync("token")
					},
					success: (res) => {
						this.cardList = res.data.data
					}
				})
			}
		},
		onPullDownRefresh() {
			uni.request({
				url: this.$http + "card/getListById",
				header: {
					'token': uni.getStorageSync("token")
				},
				success: (res) => {
					this.cardList = res.data.data
					uni.stopPullDownRefresh()
				}
			})
		},
		methods: {
			goto(id) {
				uni.navigateTo({
					url: `/pages/business/business?id=${id}`
				})
			},
			tologin() {
				uni.reLaunch({
					url: '/pages/login/login'
				})
			},
			add() {
				uni.navigateTo({
					url: "/pages/create/create"
				})
			}
		}
	}
</script>

<style scoped>
	.title {
		font-weight: 700;
		font-size: 40rpx;
		color: #8fc21f;
		padding: 40rpx 40rpx;
		margin: 10rpx 20rpx;
		border-bottom: 1px solid #f5f5f5;
		background-color: ;
	}

	.loginre {
		width: 100%;
		text-align: center;
		font-size: 32rpx;
		margin-top: 500rpx;
	}

	button {
		width: 150px;
		height: 30px;
		text-align: center;
		line-height: 30px;
		background-color: #8fc21f;
		color: #fff;
	}

	.add {
		position: fixed;
		bottom: 0;
		right: 0;
		height: 100rpx;
		width: 100rpx;
		border-radius: 50rpx;
		background-color: #8fc21f;
		color: #fff;
		text-align: center;
		line-height: 90rpx;
		font-size: 80rpx;
	}
</style>