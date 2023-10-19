<template>
	<view id="app">
		<view class="logo" :style="logo">
		</view>
		<uni-forms ref="form" class="form" :model="FormData" :rules="rules">
			<uni-forms-item label="账号" name="phone">
				<view class="easyInput">
					<uni-easyinput confirmType='next' :clearable="true" class="easyInput" v-model="FormData.phone"
						type="text" />
				</view>
			</uni-forms-item>
			<uni-forms-item label="密码" name="password">
				<view class="easyInput">
					<uni-easyinput confirmType='go' v-model="FormData.password" type="password" />
				</view>
			</uni-forms-item>
			<button @click="submit">登录</button>
		</uni-forms>
		<zero-loading v-if="loading" type="pulse" mask="true"></zero-loading>
		<uni-popup ref="popup" type="message">
			<uni-popup-message :type="type" :message="message" :duration="2000"></uni-popup-message>
		</uni-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				FormData: {
					phone: '',
					password: ''
				},
				style: {
					width: 200
				},
				index: 0,
				loading: false,
				type: "",
				message: '',
				rules: {
					phone: {
						rules: [{
							required: true,
							errorMessage: "请输入学号"
						}]
					},
					password: {
						rules: [{
							required: true,
							errorMessage: "请输入密码"
						}]
					}
				},
				logo: ""
			}
		},
		onReady() {
			this.$refs.form.setRules(this.rules)
			this.logo = `background-image:url(${this.$http}img/logo.png)`
		},
		methods: {
			submit() {
				this.$refs.form.validate([], (err, FormData) => {
					if (!err) {
						this.loading = true
						uni.request({
							url: this.$http + "user/login",
							data: FormData,
							method: 'POST',
							success: (res) => {
								this.loading = false
								if (res.data.code == 200) {
									this.type = "success"
									this.message = res.data.msg
									uni.setStorage({
										key: 'user',
										data: res.data.data
									})
									uni.setStorage({
										key: "token",
										data: res.data.data.jwt,
										success: () => {
											this.$refs.popup.open('top')
										}
									})
									setTimeout(()=>{
										uni.reLaunch({
											url:"/pages/index/index"
										})
									},2000)

								} else {
									this.type = "error"
									this.message = res.data.msg
									this.$refs.popup.open('top')
								}
							}
						})
					}
				})
			},
			setvalue(e) {
				this.FormData.index = e.detail.value
			}
		}
	}
</script>

<style scoped>
	#app {
		display: flex;
		width: 100%;
		height: 500px;
		margin: auto;
		justify-content: center;
		align-items: center;
		align-content: space-around;
		flex-direction: column;
	}

	.logo {
		height: 146px;
		width: 146px;
		background-size: contain;
		background-repeat: no-repeat;
		background-position: 0rpx 50rpx;
	}

	.form {
		height: 300px;
		/* 		display: flex;
		flex-direction: column;
		justify-content: center;
		align-content: space-around; */
	}

	.hello {
		height: 50px;
		line-height: 50px;
		text-align: center;
		font-weight: 700;
	}

	picker {
		border: 1px solid #e5e5e5;
		border-radius: 5px;
		height: 30px;
		width: 200px;
		text-align: center;
		line-height: 30px;
	}

	button {
		width: 150px;
		height: 30px;
		text-align: center;
		line-height: 30px;
		background-color: #2979ff ;
		color: #fff;
	}

	.input>text {
		height: 30px;
		text-align: center;
		line-height: 30px;
	}

	.easyInput {
		width: 200px;
	}
</style>