<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mes:csm:consumause:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mes:csm:consumause:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mes:csm:consumause:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:csm:consumause:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="consumauseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="备件批次号" align="center" prop="batchNo" />
      <el-table-column label="备件编号" align="center" prop="consuma.consumaCode" />
      <el-table-column label="备件名称" align="center" prop="consuma.consumaName" />
      <el-table-column label="规格型号" align="center" prop="consuma.specs" />
      <el-table-column label="更换周期（h）" align="center" prop="cycle" />
      <el-table-column label="更换时间" align="center" prop="replaceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.replaceTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="使用时长" align="center" prop="useTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.useTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="下一次更换时间" align="center" prop="nextReplaceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextReplaceTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mes_order_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:csm:consumause:query']"
          >更换</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:csm:consumause:remove']"
          >删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <ConsumaSelect
      ref="consumaSelect"
      @onSelected="onConsumaAdd"
    ></ConsumaSelect>
    <!-- 添加或修改在用备件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="备件批次号" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入备件批次号"/> 
        </el-form-item>


        <el-form-item label="更换周期" prop="specs">
          <el-input-number v-model="form.cycle" placeholder="请输入更换周期" @change="calculateNextTime"/> 小时
        </el-form-item>


        <el-form-item label="更换时间" prop="replaceTime">
          <el-date-picker
            v-model="form.replaceTime"
            type="datetime"
            placeholder="请选择更换时间"
            align="right"
            @change="calculateNextTime"
            value-format="yyyy-MM-dd HH:mm:ss"
            :picker-options="pickerOptions">
          </el-date-picker>
          
        </el-form-item>
        <el-form-item label="下一次更换时间" prop="nextReplaceTime" v-if="form.nextReplaceTime">
          <el-date-picker
            v-model="form.nextReplaceTime"
            type="datetime"
            placeholder="请选择下一次更换时间"
            align="right"
            value-format="yyyy-MM-dd HH:mm:ss"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
   
  </div>
</template>

<script>
import { listConsumause, getConsumause, delConsumause, addConsumause, updateConsumause } from "@/api/mes/csm/consumause";
import ConsumaSelect from "./consumaSelect.vue"
export default {
  name: "Consuma",
  dicts: ['mes_order_status'],
  components: { ConsumaSelect },
  data() {
    return {
      pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
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
        consumaName:  null
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
        cycle:null,
        attr1: null,
        attr2: null,
        attr3: null,
        attr4: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        status:null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.$refs.consumaSelect.showFlag = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const consumaUseId = row.consumaUseId || this.ids
      getConsumause(consumaUseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改在用备件";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.consumaUseId != null) {
            this.form.status = 'FINISHED';
            updateConsumause(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsumause(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const consumaUseIds = row.consumaUseId || this.ids;
      this.$modal.confirm('是否确认删除在用备件编号为"' + consumaUseIds + '"的数据项？').then(function() {
        return delConsumause(consumaUseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    // handleExport() {
    //   this.download('mes/csm/consumause/export', {
    //     ...this.queryParams
    //   }, `consumause_${new Date().getTime()}.xlsx`)
    // },

       //计算下一次更换时间
    calculateNextTime(){
      debugger;
      if(this.form.replaceTime !=null && this.form.cycle !=null){
        const replaceTime = this.form.replaceTime;
        const cycle = this.form.cycle;
        let startDate = Date.parse(new Date(replaceTime))/1000; 
        startDate += (3600) * cycle; 
        let endDate = new Date(parseInt(startDate) * 1000); 
        this.form.nextReplaceTime =endDate.getFullYear() + '-' 
                        + (endDate.getMonth()+1) + '-' 
                        + endDate.getDate() + ' '
                          + endDate.getHours() + ':' 
                          + endDate.getMinutes() + ':' 
                          + endDate.getSeconds();
      }
    },

     //备件选择回调
     onConsumaAdd(rows) {
      if (rows != null && rows.length > 0) {
        rows.forEach((row) => {
 
          const data = {
            consumaId: row.consumaId,
            deviceId: this.deviceId,
            status: 'PREPARE'
          };

          addConsumause(data).then((response) => {
            this.getList();
          });
        });
      }
    },



  }
};
</script>
