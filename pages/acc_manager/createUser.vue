<template>
	<view class="create">
		<zero-loading v-if="loading"></zero-loading>
		<view class="app">
			<view class="title">创建名片</view>
			<uni-forms ref="form" class="form" :model="FormData" :rules="rules" label-position="top"
				validate-trigger="submit">
				<uni-forms-item label="用户名" name="username" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" class="easyInput"
							v-model="FormData.username" type="easyInput" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="手机号码" name="phone" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.phone" type="easyInput" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="性别" name="gender" :required="true">
					<uni-data-select v-model="FormData.gender" :localdata="range"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item label="密码" name="password" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.password"
							type="password" />
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
				range: [{
						value: 0,
						text: "女"
					},
					{
						value: 1,
						text: "男"
					}
				],
				type: '',
				message: '',
				rules: {
					username: {
						rules: [{
							required: true,
							errorMessage: "请输入用户名"
						}]

					},
					phone: {
						rules: [{
							required: true,
							errorMessage: "请输入手机号"
						}]
					},
					gender: {
						rules: [{
							required: true,
							errorMessage: "请输入性别"
						}]
					},
					password: {
						rules: [{
							required: true,
							errorMessage: "请输入密码"
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
							this.loading = true
							if (this.FormData.gender == 0) {
								this.FormData.avatar = "girl.png"
							} else {
								this.FormData.avatar = "boy.png"
							}
							var token = uni.getStorageSync("token")
							uni.request({
								url: this.$http + "administration/createUser",
								method: "POST",
								data: this.FormData,
								header: {
									"token": token
								},
								success: (res) => {
									this.loading = false
									if (res.data.code == 200) {
										this.type = "success"
										this.message = "创建成功"
										this.$refs.popup.open('top')
										setTimeout(() => {
											this.locked = false
											uni.navigateBack({})
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