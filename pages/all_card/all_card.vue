<template>
	<view class="app">
		<view>
			<view class="title">名片管理</view>
			<view v-for="item in cardList" :key="item.id">
				<uni-card :title="item.name+'的名片'" :extra="company[item.company]" @click="goto(item.id)">
					<text class="uni-body">{{ item.remarks }}</text>
				</uni-card>
			</view>
		</view>
		<zero-loading v-if="loading" type="pulse" mask="true"></zero-loading>
	</view>
</template>

<script>
	import {
		ReactiveFlags
	} from "vue"
	export default {
		data() {
			return {
				islogin: false,
				cardList: [],
				message: "",
				loading: false,
				checkid: 0,
				type: "success",
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
					url: this.$http + "administration/getCardList",
					header: {
						'token': uni.getStorageSync("token")
					},
					success: (res) => {
						this.cardList = res.data.data
					}
				})
			}
		},
		methods: {
			tologin() {
				uni.reLaunch({
					url: '/pages/login/login'
				})
			},
			goto(id) {
				uni.navigateTo({
					url: `/pages/business/business?id=${id}`
				})
			}
		}
	}
</script>

<style scoped>
	.app {
		background-color: #eeeeee;
		height: 100vh;
	}

	.title {
		font-weight: 700;
		font-size: 40rpx;
		color: #e67e22;
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
		background-color: #2979ff;
		color: #fff;
	}

	.card-actions {
		display: flex;
		flex-direction: row;
	}

	.card-actions .card-actions-item {
		width: 50%;
		height: 40rpx;
	}

	.card-actions .card-actions-item .card-actions-item-text {
		display: block;
		height: 100%;
		width: 100%;
		text-align: center;
	}

	.fontred {
		color: red;
	}
</style>