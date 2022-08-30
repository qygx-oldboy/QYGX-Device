<template>
  <div class="app-container">
    <el-col :span="24" class="card-box">
      <el-form :model="proInspect" ref="queryForm" size="small" :inline="true">
        <!-- <el-form-item label="规格" prop="productType">
        <el-input
          v-model="proInspect.productType"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->

        <el-select
          v-model="value"
          placeholder="请选择"
          size="small"
          @change="dataScopeSelectChange"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>

        <el-autocomplete
          size="small"
          class="inline-input"
          v-model="state"
          :fetch-suggestions="querySearch"
          placeholder="全部"
          @select="handleSelect"
          clearable
          v-on:input="inputSearch($event)"
        ></el-autocomplete>

        <!-- <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
      </el-form-item> -->
      </el-form>

      <ve-histogram
        height="650px"
        :data="chartData"
        :settings="chartSettings"
        :extend="chartExtend"
        :toolbox="toolbox"
        :data-zoom="dataZoom"
        :after-set-option="afterSetOption"
      ></ve-histogram>
    </el-col>
  </div>
</template>

<script>
import { chartInspect, breedList } from "@/api/product/report";
import echarts from "echarts/lib/echarts";
import LineChartVue from "../../dashboard/LineChart.vue";
import BarChartVue from "../../dashboard/BarChart.vue";

export default {
  name: "report",

  data() {
    return {
      chartSettings: {
        yAxisType: ["0.[00]", "0.[00]%"],
        axisSite: {
          right: ["yieldRate"],
        },
        yAxisName: ["数值轴", "比率轴"],
        labelMap: {
          checkNum: "检验数",
          yieldRate: "良率",
        },
        showLine: ["yieldRate"],
      },
      chartData: {},
      chartExtend: {
        "yAxis.0.splitArea": {
          show: true,
          areaStyle: {
            color: ["#94FFAB"],
          },
        },

        color: ["blue", "red"],
        // "series.0.label": {
        //   show: true,
        //   position: "top",
        // },
        // "series.1.label": {
        //   show: true,
        //   position: "top",
        //   formatter: function (data) {
        //     return (data.data * 100).toFixed(2) + "%";
        //   },
        // },

        yAxis: {
          // 基本和xAxis中的配置一样
          splitNumber: 10, // Y轴索引间隔
        },
        tooltip: {
          padding: 5, // 提示框浮层内边距，单位px
          textStyle: {
            color: "#FFF", // 文字的颜色
            fontStyle: "normal", // 文字字体的风格（'normal'，无样式；'italic'，斜体；'oblique'，倾斜字体）
            fontWeight: "normal", // 文字字体的粗细（'normal'，无样式；'bold'，加粗；'bolder'，加粗的基础上再加粗；'lighter'，变细；数字定义粗细也可以，取值范围100至700）
            fontSize: "15", // 文字字体大小
            lineHeight: "40", // 行高
          },
          alwaysShowContent: true,
        },

        // series: {
        //   // 是否显示折线提示点
        //   showSymbol: true,
        //   // 折线提示点显示点实心
        //   symbol: "circle",
        //   // 折线是否圆滑
        //   smooth: true,
        //   // 折线提示点大小
        //   symbolSize: 5,
        //   itemStyle: {
        //     normal: {
        //       // 折线隐藏方式
        //       lineStyle: {
        //         width: 2,
        //       },
        //     },
        //   },
        // },
      },
      // 配置图标按钮(下载图片等等)
      toolbox: {
        right: 100,
        feature: {
          saveAsImage: {},
        },
      },
      // 设置区域缩放（需要引入模块才能操作）
      dataZoom: [
        {
          type: "slider",
          start: 0,
          end: 30,
          backgroundColor: "rgba(0, 0, 0, 0)", // 区域滚条背景颜色
          fillerColor: "rgba(0, 0, 0, 0.05)", // 区域滚条蒙层颜色
        },
      ],

      options: [
        {
          value: "选项1",
          label: "按品种",
        },
        {
          value: "选项2",
          label: "按玻璃材质",
        },
        {
          value: "选项3",
          label: "按规格分档",
        },
      ],

      // 按品种查询参数
      proInspect: {
        productType: undefined,
        materialQuality: undefined,
      },
      //带搜索输入框加载的数组
      restaurants: [],
      //带搜索输入框用的
      state: "",
      //按xxx分类的值
      value: "选项1",
    };
  },
  created() {
    this.getCharts();
  },

  methods: {
    getCharts() {
      chartInspect(this.proInspect).then((response) => {
        this.chartData = response.data;
      });
    },
    // /** 搜索按钮操作 */
    // handleQuery() {
    //   //this.getCharts();
    // },

    querySearch(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        );
      };
    },
    loadAll() {
      if (this.value == "选项1") {
        breedList().then((response) => {
          this.restaurants = response.data;
        });
      } else if (this.value == "选项3") {
        this.restaurants = [
          { value: "< 3.5" },
          {
            value: "3.5≤ x <6.5",
          },
          {
            value: "6.5 - 9.5",
          },
          {
            value: "≥ 9.5",
          },
        ];
      } else {
        this.restaurants = [];
      }
    },
    handleSelect(item) {
      const selectBreed = item.value.split(".");
      this.proInspect.productType = selectBreed[0];
      this.proInspect.materialQuality = selectBreed[1];
      this.getCharts();
    },
    /** 选择按xxx筛选触发 */
    dataScopeSelectChange(value) {
      this.loadAll();
    },
    inputSearch(message) {
      console.info(this.state);
      if (this.state == null || this.state == "") {
        this.proInspect = {};
        this.getCharts();
      }
    },

    //vcharts 勺子函数
    afterSetOption(chart) {
      chart.setOption({
        series: [
          {
            type: "bar",
            label: {
              show: true,
              position: "top",
            },
          },
          {
            type: "line",
            label: {
              show: true,
              position: "top",
              formatter: function (data) {
                return (data.data * 100).toFixed(2) + "%";
              }
            },
          },
        ],
      });
    },
  },
  mounted() {
    this.loadAll();
  },
};
</script>

<style>
</style>