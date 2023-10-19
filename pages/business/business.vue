<template>
	<view class="app">
		<view class="business">
			<view class="top">
				<view class="left">
					<view class="name">{{ business.name }}</view>
					<view class="position">
						<view class="post" v-for="(item,index) in business.position" :key="index">
							{{ item }}
						</view>
					</view>
				</view>
				<view class="right">
					<view :style="logo" class="img">
					</view>
				</view>
			</view>
			<view class="bottom">
				<view class="contact box">
					<view class="phone">
						<span class="icon iconfont">&#xe631;</span>
						{{ business.phone }}
					</view>
					<view class="telephone">
						<span class="icon iconfont">&#xe953;</span>
						{{ business.telephone }}
					</view>
				</view>
				<view class="email box">
					<span class="icon iconfont">&#xea9c;</span>
					{{ business.email }}
				</view>
				<view class="addr box">
					<span class="icon iconfont">&#xe606;</span>
					{{ business.address }}
				</view>
			</view>
		</view>
		<view class="card_func">
			<button class="share" open-type="share">分享名片</button>
			<view class="save" @click="save">保存名片</view>
		</view>
		<div class="bigtitle" :style="bigtitle">

		</div>
		<view class="introduce">
			<view class="background" :style="diss">

			</view>
			<view class="content">
				<view class="title_logo" :style="titlelogo">

				</view>
				<view class="text">
					<p>
						五洲工程顾问集团有限公司（以下简称“五洲工程顾问集
						团”)是一家以全过程工程咨询和工程总承包为核心产品
						的顾问工程公司，总部位于浙江杭州。公司提供工程代
						建（项目管理）、工程监理、工程设计等传统咨询服
						务，并提供医疗建筑咨询、投资决策咨询、设计管理、
						全过程投资控制、双碳（绿建）咨询、BM咨询、数字
						咨询、安全顾问、法律顾问等新型咨询服务，是国内领
						先的一站式综合性品牌服务商。
					</p>
					<p>
						五洲工程顾问集团以政府智库、业主顾问为定位，作为
						国家级高新技术企业，浙江省未来社区建设技术支撑单
						位，获批成立浙江省高新技术企业研究开发中心和省级
						博士后工作站，拥有深厚的科技研发能力、完备的技术
						研发体系、强大的高端人才团队。发展至今，五洲工程
						顾问集团已成为全过程工程咨询领域的佼佼者，连续多
						年位列住建部监理行业综合排名前5、蝉联中国医院基建
						十佳服务商，并先后荣膺全国企业党建工作先进单位、
						杭州市清廉民营企业培育示范点单位、浙商最具投资潜
						力企业等荣誉，综合实力浙江领先，位居全国前列。
					</p>
				</view>
			</view>

		</view>
		<div class="bigtitle" :style="bigtitledb">

		</div>
		<div class="dbal" :style="dbal"></div>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				id: 0,
				logo: "",
				business: {},
				logoList: ["logo.png", "qiancheng_logo.png"],
				bigtitle: '',
				titlelogo: '',
				bigtitledb: '',
				dbal: '',
				diss: ''
			}
		},
		methods: {
			save() {
				uni.addPhoneContact({
					nickName: this.business.name,
					lastName: this.business.name.slice(0, 1),
					firstName: this.business.name.slice(1),
					mobilePhoneNumber: this.business.phone,
					organization: this.business.company == 0 ? "五洲工程" : "千城建筑",
					title: this.business.position[0],
					hostNumber: this.business.telephone,
					email: this.business.email
				})
			}
		},
		onLoad(option) {
			this.id = JSON.parse(decodeURIComponent(option.id))
		},

		onShareAppMessage() {
			return {
				title: this.business.name + '的名片',
				path: '/pages/business/business?id='+this.id,
				imageUrl: this.$http + "/img/logo.png",
			}
		},
		onShareTimeline() {
			return {
				title: this.business.name + '的名片',
				path: '/pages/business/business?id='+this.id,
				imageUrl: this.$http + "/img/logo.png",
			}
		},
		onReady() {
			if (this.id != 0) {
				uni.request({
					url: this.$http + "card/getById?id=" + this.id,
					success: (res) => {
						var bus = res.data.data
						bus.position = bus.position.split(",")
						this.business = bus
						this.logo = `background-image:url(${this.$http}img/${this.logoList[bus.company]})`
						this.bigtitle = `background-image:url(${this.$http}img/introduce_title.png)`
						this.titlelogo = `background-image:url(${this.$http}img/introduce_wuzhou_logo.png)`
						this.diss = `background-image:url(${this.$http}img/introduce_wuzhou_background.png)`
						this.bigtitledb = `background-image:url(${this.$http}img/introduce_title_db.png)`
						this.dbal = `background-image:url(${this.$http}img/introduce_wuzhou_dbal.png)`
					}
				})
			}
		}
	}
</script>

<style>
	.app {
		border: #eeeeee 1px solid;
		background-color: #eeeeee;
	}

	.business {
		display: flex;
		background-color: #fff;
		border-radius: 20rpx;
		padding: 40rpx;
		margin: 20rpx;
		flex-direction: column;
		font-size: 24rpx;
		box-shadow: #e5e5e5 0rpx 5rpx 5rpx 5rpx;
	}

	.top {
		display: flex;
		justify-content: row;
		border-bottom: #dadcdb 1px solid;
		padding-bottom: 20px;
	}

	.left {
		width: 65%;
		line-height: 48rpx;
	}

	.left .name {

		font-size: 50rpx;
	}

	.left .position {
		margin-top: 40rpx;
	}

	.right {
		width: 30%;
	}

	.right .img {
		width: 100%;
		height: 100%;
		background-size: contain;
		background-repeat: no-repeat;
	}

	.bottom {
		display: flex;
		margin-top: 40rpx;
		flex-direction: column;

	}

	.bottom .contact {
		display: flex;
		flex-direction: row;
	}

	.bottom .telephone {
		margin-left: 200rpx;
	}

	.bottom .box {
		margin: 10rpx 0rpx;
	}

	.card_func {
		display: flex;
		padding: 20rpx;
		flex-direction: row;
	}

	.card_func .share,
	.card_func .save {
		height: 80rpx;
		line-height: 80rpx;
		width: 50%;
		text-align: center;
		box-shadow: #d5ded3 0rpx 5rpx 5rpx 5rpx;
	}

	.card_func .share {
		background-color: #8fc21f;
		color: #fff;
		border-radius: 20rpx;
		margin-right: 10rpx;
		outline: none;
		border: none;
	}

	button::after {

		border: none;

	}

	.card_func .save {
		background-color: #fff;
		color: #8fc21f;
		border-radius: 20rpx;
		margin-left: 10rpx;
	}

	.box .icon {
		margin-right: 5rpx;
	}

	.introduce {
		background-color: #fff;
		border-radius: 20rpx;
		padding: 40rpx;
		margin: 20rpx;
	}

	.text {
		border: 1px solid #e5e5e5;
		padding: 40rpx;
		border-radius: 10rpx;
	}

	.text p {
		margin: 20rpx 0rpx;
	}

	.bigtitle {
		margin: 20rpx;
		width: 150rpx;
		height: 50rpx;
		background-repeat: no-repeat;
		background-size: contain;
	}

	.title_logo {
		position: relative;
		height: 250rpx;
		height: 50rpx;
		top: 30rpx;
		background-repeat: no-repeat;
		background-size: contain;
	}

	.dbal {
		height: 700rpx;
		background-repeat: no-repeat;
		background-size: cover;
		margin: 40rpx;
	}

	.background {
		width: 100%;
		height: 400rpx;
		background-repeat: no-repeat;
		background-size: contain;

	}
</style>