<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备编码" prop="machineryCode">
        <el-input
          v-model="queryParams.machineryCode"
          placeholder="请输入设备编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="machineryName">
        <el-input
          v-model="queryParams.machineryName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录日期" prop="recordDate">
        <el-date-picker clearable
          v-model="queryParams.recordDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="稼动率" prop="utilizationRate">
        <el-input
          v-model="queryParams.utilizationRate"
          placeholder="请输入稼动率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="runList">
     
      <el-table-column label="记录ID" align="center" prop="recordId" />
      <el-table-column label="设备编码" align="center" prop="machineryCode" />
      <el-table-column label="设备名称" align="center" prop="machineryName" />
      <el-table-column label="记录日期" align="center" prop="recordDate">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="班次名称" align="center" prop="shiftName" />
      <el-table-column label="开机时间" align="center" prop="startTime" >
      </el-table-column>
      <el-table-column label="停行时间" align="center" prop="endTime">
  
      </el-table-column>
      <el-table-column label="运行时间" align="center" prop="runTime" />
      <!-- <el-table-column label="计划停机" align="center" prop="planDownTime" />
      <el-table-column label="异常停机时长" align="center" prop="excepitonDownTime" />
      <el-table-column label="稼动时间" align="center" prop="movingTime" /> -->
      <el-table-column label="稼动率" align="center" prop="utilizationRate" />
      
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    
  </div>
</template>

<script>
import { listRun, getRun, delRun, addRun, updateRun } from "@/api/device/run";

export default {
  name: "Run",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 运行记录表格数据
      runList: [],
    
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        machineryCode: null,
        machineryName: null,
        recordDate: null,
        utilizationRate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询运行记录列表 */
    getList() {
      this.loading = true;
      listRun(this.queryParams).then(response => {
        this.runList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        recordId: null,
        traceId: null,
        deviceId: null,
        machineryCode: null,
        machineryName: null,
        recordDate: null,
        startTime: null,
        endTime: null,
        runTime: null,
        planDownTime: null,
        excepitonDownTime: null,
        movingTime: null,
        utilizationRate: null,
        productParams: null,
        attr1: null,
        attr2: null,
        attr3: null,
        attr4: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
   
  }
};
</script>
