<template>
	<div class="card">
		<div class="top">
			<div class="name">
				{{ card.name }}
			</div>
			<div class="company">
				{{ card.company }}
			</div>
		</div>
		<div class="bottom">
			<div class="remarks">
				{{ card.remarks }}
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		props:{
			card:{
				type:Object
			}
		}
	}
</script>

<style scoped>
	.card {
		width: 90%;
		height: 200rpx;
		margin: auto;
		padding: 20rpx;
		background-color: #fff;
		box-shadow: 0px 0px 6px 5px #f5f5f5;
		border-radius: 5px;
	}

	.top {
		display: flex;
		padding: 10rpx 20rpx;
		flex-direction: row;
		justify-content: space-between;
		border-bottom: 1rpx #f5f5f5 solid;
	}
	.company{
		font-size: 16px;
		color: #9f9f9f;
	}
	.bottom{
		padding: 10rpx 20rpx;
	}

</style>