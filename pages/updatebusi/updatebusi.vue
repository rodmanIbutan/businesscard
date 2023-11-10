<template>
	<view class="create">
		<zero-loading v-if="loading"></zero-loading>
		<view class="app">
			<view class="title">创建名片</view>
			<uni-forms ref="form" class="form" :model="FormData" :rules="rules" label-position="top"
				validate-trigger="submit">
				<uni-forms-item label="标题" name="title" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" class="easyInput" v-model="FormData.title"
							type="easyInput" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="姓名" name="name" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.name" type="easyInput" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="公司" name="company" :required="true">
					<uni-data-select v-model="FormData.company" :localdata="range"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item label="手机号码" name="phone" :required="true">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.phone" type="easyInput" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="公司电话" name="telephone">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.telephone"
							type="easyInput" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="邮箱" name="email">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.email" type="easyInput" />
					</view>
				</uni-forms-item>
				<uni-forms-item label="地址" name="address1" :required="true" v-if="FormData.company==0">
					<uni-data-select v-model="addrC" :localdata="wuzhouAddr"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item label="地址" name="address2" :required="true" v-if="FormData.company==1">
					<uni-data-select v-model="addrC" :localdata="qcAddr"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item label="自定义" name="address3" :required="true" v-if="addrC==4">
					<view class="easyInput">
						<uni-easyinput confirmType='next' :clearable="true" v-model="FormData.address"
							type="easyInput" />
					</view>
				</uni-forms-item>
				<view v-for="index in count" :key="index">
					<uni-forms-item :label="'职位'+(index+1)" :name="'position'+index">
						<view class="easyInput">
							<uni-easyinput confirmType='next' :clearable="true" v-model="positionArry[index]"
								type="easyInput" />
						</view>
					</uni-forms-item>
				</view>
				<view class="add" @click="add()" v-if="count<8">
					+
				</view>
				<uni-forms-item label="备注" name="remarks">
					<view class="easyInput">
						<uni-easyinput confirmType='go' :clearable="true" v-model="FormData.remarks" type="easyInput" />
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
				count: 1,
				loading: false,
				positionArry: [],
				FormData: {
					name: '',
					title: '',
					remarks: '',
					phone: '',
					telephone: '',
					email: '',
					address: '',
					position: '',
					company: '泛微'
				},
				range: [{
						value: 0,
						text: this.$company[0]
					},
					{
						value: 1,
						text: this.$company[1]
					}
				],
				wuzhouAddr: [{
						value: 0,
						text: this.$publicAddress.wuzhou[0]
					},
					{
						value: 1,
						text: this.$publicAddress.wuzhou[1]
					},
					{
						value: 2,
						text: this.$publicAddress.wuzhou[2]
					},
					{
						value: 3,
						text: this.$publicAddress.wuzhou[3]
					},
					{
						value: 4,
						text: "其他"
					}
				],
				qcAddr: [{
						value: 0,
						text: this.$publicAddress.qiancheng[0]
					},
					{
						value: 1,
						text: this.$publicAddress.qiancheng[1]
					},
					{
						value: 4,
						text: "其他"
					}
				],
				addrC: '',
				type: '',
				message: '',
				rules: {
					title: {
						rules: [{
							required: true,
							errorMessage: "请输入标题"
						}]

					},
					name: {
						rules: [{
							required: true,
							errorMessage: "请输入姓名"
						}]
					},
					phone: {
						rules: [{
							required: true,
							errorMessage: "请输入手机号"
						}]
					},
					address: {
						rules: [{
							required: true,
							errorMessage: "请输入地址"
						}]
					}
				}
			}
		},
		onShow() {
			this.FormData = {
				locked: false,
				name: '',
				title: '',
				remarks: '',
				phone: '',
				telephone: '',
				email: '',
				address: '',
				position: '',
				company: '泛微'
			}
			this.count = 1
			this.positionArry = []
		},
		onLoad(option) {
			this.id = JSON.parse(decodeURIComponent(option.id))
		},
		onReady() {
			if (this.id != 0) {
				uni.request({
					url: this.$http + "card/getById?id=" + this.id,
					success: (res) => {
						var bus = res.data.data
						var arr = bus.position.split(",")
						this.FormData = bus
						this.FormData.address = ''
						this.count = arr.length
						this.positionArry = arr
					}
				})
			}
		},
		methods: {
			add() {
				if (this.positionArry[this.count - 1] != null) {
					this.count++
				} else {
					this.type = "error"
					this.message = "请输入职位" + this.count
					this.$refs.popup.open('top')
				}
			},
			submit() {
				if (!this.locked) {
					this.locked = true
					this.$refs.form.validate([], (err, FormData) => {
						if (!err) {
							if (this.positionArry.length == 0) {
								this.type = "error"
								this.message = "请至少输入一个职位"
								this.$refs.popup.open('top')
								this.locked = false
							}  else if (this.addrC == 4 && this.FormData.address == ''||this.addrC === '') {
								console.log(this.addrC,2)
								this.type = "error"
								this.message = "请输入地址"
								this.$refs.popup.open('top')
								this.locked = false
							} else {
								if (this.addrC != 4) {
									this.FormData.address = this.FormData.company == 0 ? this.$publicAddress
										.wuzhou[this.addrC] : this.$publicAddress.qiancheng[this.addrC]
								}
								if(this.FormData.remarks===''){
									this.FormData.remarks=this.$company[this.FormData.company]
								}
								this.loading = true
								var arr = this.positionArry.filter(function(s) {
									return s && s.trim()
								})
								this.FormData.position = arr.toString()
								var token = uni.getStorageSync("token")
								uni.request({
									url: this.$http + "card/update",
									method: "POST",
									data: this.FormData,
									header: {
										"token": token
									},
									success: (res) => {
										this.loading = false
										this.type = "success"
										this.message = "修改成功"
										this.$refs.popup.open('top')
										setTimeout(() => {
											this.locked = false
											uni.reLaunch({
												url: "/pages/manager/manager"
											})
										}, 1000)
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
		background-color: #2979ff;
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
		background-color: #2979ff;
		color: #fff;
	}
</style>