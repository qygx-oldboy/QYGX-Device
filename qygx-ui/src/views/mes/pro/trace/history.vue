<template>
  <div class="app-container">
    <!-- 展示物流进度的对话框 -->
   
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in activities"
          :key="index"
          :icon="activity.icon"
          :type="activity.type"
          :color="activity.color"
          :size="activity.size"
          :timestamp="activity.timestamp"
        >
        
        <p>{{ activity.content }}</p>
        <p>{{ activity.operator }}</p>
        <p>{{ activity.device }}</p>
        <el-table
          :data="activity.runParam"
          v-if="activity.runParam"
          border
          style="width: 35%">
        
          <el-table-column
            prop="paramName"
            label="参数名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="paramValue"
            label="参数值">
          </el-table-column>
        </el-table>
        </el-timeline-item>
      </el-timeline>
   
  </div>
</template>

<script>
import {
  listTrace,
  getTrace,
  delTrace,
  addTrace,
  updateTrace,
  TraceHistory
} from "@/api/mes/pro/trace";

export default {
  name: "TraceHistory",
  data() {
    return {
      
      // 弹出层标题
      title: "",
      // 查询参数
      queryParams: {
        itemCode: null
      },
      activities: [
      ],
  
    
    };
  },
  created() {
    const itemCode = this.$route.params && this.$route.params.itemCode;
    this.queryParams.itemCode = itemCode;
    this.getList();
  },
  methods: {

    /** 查询生产追溯列表 */
    getList() {
      TraceHistory(this.queryParams).then(response => {

        for (const iterator of response.data) {
          var date = new Date(iterator.timestamp);
          var localeString = date.toLocaleString();
          iterator.timestamp = localeString;
       }
       this.activities = response.data;
       
      });
    },
  },
};
</script>

<style scoped>
::v-deep .el-timeline-item__timestamp.is-bottom {
    position: absolute;
    left: -117px;
    top: -3px;
    color: #333333;
}
::v-deep .el-timeline {
    padding-left: 150px;
}
</style>