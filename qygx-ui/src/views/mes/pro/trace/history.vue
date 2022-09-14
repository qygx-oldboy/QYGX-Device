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
        <!-- <h4> {{ activity.title }}</h4> -->
        <p>{{ activity.content }}</p>
    
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
        pageNum: 1,
        pageSize: 10,
      },
      activities: [
        {
        
          content: '快件已签收 签收人：家人 感谢使用圆通快递 期待再次为您服务',
          timestamp: '2018-04-15 13:07:40',
          size: 'large',
          type: 'primary',
          icon: 'el-icon-more'
        },
        {
       
          content:
            '[北京市]北京海淀育新小区营业点派件员 顺丰速运 95338正在为您派件',
          timestamp: '2018-05-10 07:32:00',
          color: '#0bbd87'
        },
        {
       
          content: '快件到达 [北京海淀育新小区营业点]',
          timestamp: '2018-05-10 08:23:00',
        },
        {
       
          content:
            '快件在[北京顺义集散中心]已装车,准备发往 [北京海淀育新小区营业点]',
          timestamp: '2018-05-10 02:03:00',
        },
        {
        
          content: '快件到达 [北京顺义集散中心]',
          timestamp: '2018-05-09 23:05:00',
        },
        {
    
          content: '快件在[北京宝胜营业点]已装车,准备发往 [北京顺义集散中心]',
          timestamp: '2018-05-09 21:21:00',
        },
        {
      
          content: '商品已经下单',
          timestamp: '2018-05-08 21:36:04',
        },
      ],
    
    };
  },
  created() {
    //const itemCode = this.$route.params && this.$route.params.itemCode;
    this.getList()
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
       //this.activities = response.data;
      });
    },
  },
};
</script>

