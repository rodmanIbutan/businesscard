<template>
	<view class="app">
		<view>
			<view class="title">名片管理</view>
			<view v-for="item in cardList" :key="item.id">
				<uni-card :title="item.title" :extra="item.company">
					<text class="uni-body">{{ item.remarks }}</text>
					<view slot="actions" class="card-actions">
						<view class="card-actions-item" @click="update(item.id)">
							<text class="card-actions-item-text">修改</text>
						</view>
						<view class="card-actions-item fontred" @click="open(item.id)">
							<text class="card-actions-item-text">删除</text>
						</view>
					</view>
				</uni-card>
			</view>
		</view>
		<uni-popup ref="delete" type="dialog">
			<uni-popup-dialog mode="base" :content="message" :duration="2000" :before-close="true" @close="close"
				@confirm="confirm"></uni-popup-dialog>
		</uni-popup>
		<uni-popup ref="popup" type="message">
			<uni-popup-message :type="type" :message="message" :duration="2000"></uni-popup-message>
		</uni-popup>
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
				type: "success"
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
		methods: {
			tologin() {
				uni.reLaunch({
					url: '/pages/login/login'
				})
			},
			open(e) {
				this.message = "确认删除？"
				this.$refs.delete.open()
				this.checkid = e
			},
			close() {
				this.checkid = 0
				this.$refs.delete.close()
			},
			confirm() {
				this.loading = true
				if (this.checkid == 0) {
					this.message = "出错了"
					this.type = "error"
					this.$refs.popup.open()
				} else {
					uni.request({
						url: this.$http + "card/delete?id=" + this.checkid,
						header: {
							"token": uni.getStorageSync("token")
						},
						success: (res) => {
							if (res.data.code == 200) {
								this.loading = false
								this.$refs.delete.close()
								this.message = "删除成功"
								this.type = "success"
								this.$refs.popup.open()
								this.reload()

							}
						}
					})
				}
			},
			reload() {
				this.loading = true
				uni.request({
					url: this.$http + "card/getListById",
					header: {
						'token': uni.getStorageSync("token")
					},
					success: (res) => {
						this.cardList = res.data.data
						this.loading = false
					}
				})
			},
			update(id){
				uni.navigateTo({
					url:"/pages/updatebusi/updatebusi?id="+id
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