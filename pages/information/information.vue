<template>
	<view>
		<view class="my-background-circle bg-topic-theme"></view>
		<view class="information">
			<uni-popup ref="popup" type="message">
				<uni-popup-message type="warn" message="请先登录" :duration="1000"></uni-popup-message>
			</uni-popup>
			<view class="my-background-circle bg-topic-theme"></view>
			<view v-if="!islogin">
				<view class="user-information" @click="login">
					<img src="/static/img/noLoginAvatar.png" alt="" class="avatar">
					<view class="nickname">
						未登录，点击登录~
					</view>
				</view>
				<img src="/static/img/empty_groupon.png" alt="" class="nolog-img">
				<view class="nolog-title">
					请先登录~
				</view>
			</view>
			<view v-if="islogin">
				<view>
					<view class="user-information">
						<img :src="user.avatar" alt="" class="avatar">
						<view class="nickname">
							{{user.username}}
						</view>
					</view>
				</view>
				<view class="release" @click="goto('manager')">
					管理名片
				</view>
				<view class="release" v-if="admin" @click="goto('acc_manager')">账号管理</view>
				<view class="release" v-if="admin" @click="goto('all_card')">查看所有名片</view>
				<view class="accept" @click="goto('changepwd')">
					修改密码
				</view>
				<view class="logout" @click="open">
					退出登录
				</view>
				<img src="/static/img/empty_groupon.png" alt="" class="nolog-img" v-if="!islogin">
				<uni-popup ref="popuplogout" type="dialog">
					<uni-popup-dialog mode="base" content="是否退出登录" :duration="2000" :before-close="true" title=" "
						confirmText="确定" cancelText="手滑了" @close="close" @confirm="confirm"></uni-popup-dialog>
				</uni-popup>
			</view>


		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				Id: 0,
				sWhere: "",
				islogin: false,
				user: {},
				admin: false
			}
		},
		onLoad() {},
		onShow() {
			if (uni.getStorageSync("token")) {
				this.islogin = true
			}
		},
		onReady() {
			var userr = uni.getStorageSync("user")
			userr.avatar = this.$http + "img/" + userr.avatar
			this.user = userr
			if (this.user.id == 1) {
				this.admin = true
			}
		},

		methods: {
			login() {
				uni.navigateTo({
					url: "/pages/login/login"
				})
			},
			logout() {
				uni.removeStorageSync("token");
				uni.removeStorageSync("user")
				uni.reLaunch({
					url: '/pages/index/index'
				})
			},
			open() {
				this.$refs.popuplogout.open()
			},
			close() {
				this.$refs.popuplogout.close()
			},
			confirm() {
				this.logout()
			},
			goto(url) {
				uni.navigateTo({
					url: `/pages/${url}/${url}`
				})
			}

		},
	}
</script>

<style lang="scss" scoped>
	.logout {
		position: absolute;
		margin: auto;
		bottom: 50rpx;
		left: 50%;
		margin-left: -42.5%;
		height: 50rpx;
		width: 85%;
		padding-top: 25rpx;
		background-color: #ff786c;
		font-size: 36rpx;
		text-align: center;
		line-height: 25rpx;
		color: #fff;
		border-radius: 20rpx;
	}

	.logout:active {
		background-color: #dedede;
	}

	.user-information {
		position: relative;
		height: 200rpx;
	}

	.avatar {
		position: absolute;
		left: 10%;
		top: 40rpx;
		width: 140rpx;
		height: 140rpx;
		border-radius: 70rpx;
		border: 2rpx #ffffff solid;


	}

	.nickname {
		position: absolute;
		left: 35%;
		top: 100rpx;
		font-size: 32rpx;
	}

	.nolog-img {
		position: absolute;
		width: 100%;
		bottom: 350rpx;
	}

	.nolog-title {
		position: absolute;
		width: 100%;
		text-align: center;
		bottom: 350rpx;
		color: #aaaaaa;
	}

	.release {
		// position: absolute;
		// top: 200rpx;
		margin: auto;
		margin-top: 100rpx;
		height: 60rpx;
		width: 85%;
		padding-top: 30rpx;
		padding-left: 70rpx;
		background-color: #f0f0f0;
		font-size: 30rpx;
		text-align: left;
		line-height: 30rpx;
		color: #000000;
		border-radius: 10px;
	}

	.release:active {
		background-color: #dedede;
	}

	.accept {
		margin: auto;
		height: 60rpx;
		width: 85%;
		padding-top: 30rpx;
		padding-left: 70rpx;
		background-color: #f0f0f0;
		font-size: 30rpx;
		text-align: left;
		color: #000000;
		border-radius: 10px;
		margin-top: 200rpx;
		line-height: 30rpx;
	}

	.accept:active {
		background-color: #dedede;
	}

	.bg-topic-theme {
		background-color: #8fc21f;
		color: #333333;
	}

	.my-background-circle {
		position: absolute;
		width: 100%;
		height: 200rpx;
		/* #ifdef APP-PLUS */
		height: 240rpx;
		/* #endif */
		height: 280rpx;
		border-radius: 0 0 50% 50% / 0% 0% 15% 15%;
		overflow: hidden;
		z-index: -1;
	}
</style>