<template>
    <el-container>
    <el-header>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户统计</el-breadcrumb-item>
        </el-breadcrumb>
    </el-header>
        <el-main>
            <div id="main" ></div>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "UserSta",
        mounted() {
            this.userEcharts();
        },
        methods:{
            userEcharts(){//写在这个里面，到哪里都可以调用
                // 基于准备好的dom，初始化echarts实例
                var myChart = this.$echarts.init(document.getElementById('main'));
                //操作了页面数据
                //获取数据
                this.$axios
                    .post("user/countUserAddress")
                    .then(rst=>{
                        console.log(rst);
                        this.depts=rst.data;

                        for(var i = 0;i < rst.data.length;i++)
                        {
                            var param = {"name":rst.data[i].name,"value":rst.data[i].value,};
                            option1.series[0].data.push(param);
                        };
                        console.log(option1.series[0].data);
                        myChart.setOption(option1);
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
                //var option2 = rst.data.data
                // 指定图表的配置项和数据
                var option1 = {
                    title: {
                        text: '猿来如此点用户访问来源',
                        subtext: '纯属虚构',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                    },
                    series: [
                        {
                            name: '地区人数',
                            type: 'pie',
                            radius: '50%',
                            data: [],
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
            }
        },
    }
</script>

<style scoped>

</style>