<template>
  <div class="app-container">
    <el-col :span="24" class="card-box">
      <el-card>
        <div slot="header"><span>基本信息</span></div>
        <div class="el-table el-table--enable-row-hover el-table--medium">
          <table cellspacing="0" style="width: 100%">
            <tbody>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">日期</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell">{{ info.date }}</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell">班次</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell">{{ info.ban }}</div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </el-card>
    </el-col>

    <el-col :span="24" class="card-box">
      <el-card>
        <div slot="header"><span>实时信息</span></div>
        <el-form
          :model="proInspect"
          ref="queryForm"
          size="small"
          :inline="true"
        >
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
        </el-form>

        <ve-line
          height="450px"
          :data="chartData"
          :settings="chartSettings"
          :extend="chartExtend"
          :toolbox="toolbox"
          :data-zoom="dataZoom"
        ></ve-line>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import { breedList } from "@/api/product/report";
import { chartGetShift, getInfo } from "@/api/product/quality";

export default {
  name: "quality",

  data() {
    return {
      // 基础信息
      info: {
        date: undefined,
        ban: undefined,
      },
      chartSettings: {
        yAxisType: ["0.[00]%"],
        // axisSite: {
        //   right: ["yieldRate"],
        // },
        // yAxisName: ["数值轴", "比率轴"],
        labelMap: {
          //   checkNum: "检验数",
          yieldRate: "良率",
        },
      },
      chartData: {},
      chartExtend: {
        "yAxis.0.splitArea": {
          show: true,
          areaStyle: {
            color: ["#94FFAB"],
          },
        },

        color: ["red"],
        series: {
          type: "line",
          label: {
            show: true,
            position: "top",
            formatter: function (data) {
           
              return (data.data[1] * 100).toFixed(1) + "%";
            }
          }
        },
        yAxis: {
          // 基本和xAxis中的配置一样
          splitNumber: 10, // Y轴索引间隔
        },
        tooltip: {
          alwaysShowContent: true,
        },
      },
      // 配置图标按钮(下载图片等等)
      toolbox: {
        // right: 100,
        // feature: {
        //   saveAsImage: {},
        // },
      },
      // 设置区域缩放（需要引入模块才能操作）
      dataZoom: [
        {
          type: "slider",
          start: 0,
          end: 100,
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
    this.getInfo();
    this.getCharts();
  },

  methods: {
    getCharts() {
      chartGetShift(this.proInspect).then((response) => {
        console.info(response.data);
        this.chartData = response.data;
      });
    },

    getInfo() {
      getInfo().then((response) => {
        this.info = response.data;
      });
    },

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
      if (this.state == null || this.state == "") {
        this.proInspect = {};
        this.getCharts();
      }
    },
  },
  mounted() {
    this.loadAll();
    let timer = setInterval(() => {
      //需要定时执行的代码
      this.getCharts();
    }, 60000);
  },
};
</script>

<style>
</style>