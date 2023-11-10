<template>
	<view class="create">
		<zero-loading v-if="loading"></zero-loading>
		<view class="app">
			<view class="title">创建名片</view>
			<uni-forms ref="form" class="form" :model="FormData" :rules="rules" label-position="top"
				validate-trigger="submit">
				<uni-forms-item label="旧密码" name="password" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.password"
							type="password" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="新密码" name="npassword" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.npassword"
							type="password" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="确认密码" name="qnpassword" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="qnpassword" type="password" />
					</view>
				</uni-forms-item>
				<button @click="submit()">提交</button>
			</uni-forms>
			<uni-popup ref="popup" type="message">
				<uni-popup-message :type="type" :message="message" :duration="2000"></uni-popup-message>
			</uni-popup>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				loading: false,
				FormData: {},
				type: '',
				message: '',
				qnpassword: '',
				rules: {
					password: {
						rules: [{
							required: true,
							errorMessage: "请输入旧密码"
						}]
					},
					npassword: {
						rules: [{
							required: true,
							errorMessage: "请输入新密码"
						}]
					}
				}
			}
		},
		onShow() {
			this.FormData = {}
			this.locked = false
		},
		methods: {
			submit() {
				if (!this.locked) {
					this.locked = true
					this.$refs.form.validate([], (err, FormData) => {
						if (!err) {
							if (this.FormData.npassword != this.qnpassword) {
								this.type = "error"
								this.message = "密码不一致"
								this.$refs.popup.open('top')
							} else {
								this.loading = true
								var token = uni.getStorageSync("token")
								console.log(this.FormData)
								uni.request({
									url: this.$http + "user/updatePassword",
									method: "POST",
									data: this.FormData,
									header: {
										"token": token
									},
									success: (res) => {
										console.log(res)
										this.loading = false
										if (res.data.code == 200) {
											this.type = "success"
											this.message = "修改成功"
											this.$refs.popup.open('top')
											setTimeout(() => {
												this.locked = false
												uni.clearStorageSync()
												uni.reLaunch({
													url:"/pages/index/index"
												})
											}, 1000)
										} else {
											this.locked = false
											this.type = "error"
											this.message = res.data.msg
											this.$refs.popup.open('top')
										}

									}
								})
							}
						}
					})
				}
			}
		}
	}
</script>

<style scoped>
	.create {
		height: 100vh;
		background-color: #eeeeee;
		border: 1px #eeeeee solid;
	}

	.app {
		margin: 20rpx;
		background-color: #fff;
		border-radius: 20rpx;
		padding: 20rpx;
	}

	.add {
		width: 60rpx;
		height: 60rpx;
		border-radius: 30rpx;
		background-color: #8fc21f;
		text-align: center;
		line-height: 55rpx;
		font-size: 48rpx;
		color: #fff;
	}

	button {
		width: 600rpx;
		height: 80rpx;
		text-align: center;
		line-height: 80rpx;
		background-color: #8fc21f;
		color: #fff;
	}
</style>