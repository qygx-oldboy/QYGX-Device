<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="过期时间" prop="expireDay">
        <el-select v-model="queryParams.expireDay" placeholder="请选择"  clearable>
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="备件名称" prop="consumaName">
        <el-input
          v-model="queryParams.consumaName"
          placeholder="请输入备件名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>




      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>



    <el-table v-loading="loading" :data="consumauseList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="备件编号" align="center" prop="consuma.consumaCode" />
      <el-table-column label="备件名称" align="center" prop="consuma.consumaName" />
      <el-table-column label="规格型号" align="center" prop="consuma.specs" />
      <el-table-column label="更换周期（h）" align="center" prop="cycle" />
      <el-table-column label="更换时间" align="center" prop="replaceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.replaceTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="使用时长" align="center" prop="useTime">
        <template slot-scope="scope">
          <div :style="{ color: Date.parse(scope.row.nextReplaceTime) < Date.now() ? 'red' : '' }">
            <span>{{ scope.row.useTime }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="下一次更换时间" align="center" prop="nextReplaceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextReplaceTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="设备编码" align="center" prop="device.deviceCode">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleConsuma(scope.row.device.deviceId)"
            v-hasPermi="['mes:csm:consumause:edit']"
            >{{ scope.row.device.deviceCode }}</el-button
          >
        </template>

      </el-table-column>
      <el-table-column label="设备名称" align="center" prop="device.name" />
      <el-table-column label="备注" align="center" prop="remark" width="200px"/>
     

      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:csm:consumause:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:csm:consumause:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
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
import { listConsumause, getConsumause, delConsumause, addConsumause, updateConsumause } from "@/api/mes/csm/consumause";
export default {
  name: "Consumause",
  data() {
    return {

      options: [ {
          value: 1,
          label: '已过期'
        }, {
          value: 3,
          label: '3天以上'
        }, {
          value: 7,
          label: '7天以上'
        }, {
          value: 30,
          label: '1个月以上'
        },
        {
          value: 90,
          label: '3个月以上'
        }
      
      
      ],
      deviceId: null,
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
      // 在用备件表格数据
      consumauseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceId: null,
        consumaName:  null,
        expireDay: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
      //从上级页面获取的规则ID
    const deviceId = this.$route.query.deviceId;
    this.deviceId = deviceId;
    this.queryParams.deviceId = deviceId;
    this.getList();
  },
  methods: {
    /** 查询在用备件列表 */
    getList() {
      this.loading = true;
      listConsumause(this.queryParams).then(response => {
        this.consumauseList = response.rows;
       
        for (const consumause of this.consumauseList) {
          const startTime = consumause.replaceTime;
          if(startTime){
            const mss = Date.now() - Date.parse(startTime); // 当前时间的时间戳 -  开始时间的时间戳
    
            const days = parseInt(mss/(1000 * 60 * 60 * 24));
            const hours = parseInt((mss % (1000 * 60 * 60 * 24)) /(1000 * 60 * 60));
            consumause.useTime = days + "天" + hours + "小时"
          }
        }
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
        consumaUseId: null,
        consumaId: null,
        deviceId: null,
        replaceTime: null,
        useTime: null,
        nextReplaceTime: null,
        remark: null,
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.consumaUseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    

    //备件
    handleConsuma(deviceId){
      this.$router.push({ path: '/device/consuma/index',query: { deviceId: deviceId || 0}})
    },

  }
};
</script>
