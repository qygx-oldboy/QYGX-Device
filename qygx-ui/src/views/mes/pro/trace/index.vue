<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="产品/物料编码" prop="itemCode">
        <el-input
          v-model="queryParams.itemCode"
          placeholder="请输入产品物料编码"
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
          v-hasPermi="['mes:pro:trace:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mes:pro:trace:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mes:pro:trace:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:pro:trace:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="traceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="追溯ID" align="center" prop="traceId" />
      <el-table-column label="产品物料编码" align="center" prop="itemCode" width="200" >
        <template slot-scope="scope">
          <router-link :to="'/mes/trace-history/index/' + scope.row.itemCode" class="link-type">
            <span>{{ scope.row.itemCode }}</span>
          </router-link>
        </template>
      </el-table-column>
      <!-- <el-table-column label="产品物料名称" align="center" prop="itemName" /> -->
      <!-- <el-table-column label="工序编码" align="center" prop="processCode" width="140"/> -->
      <el-table-column label="工序名称" align="center" prop="processName" />
      <!-- <el-table-column label="设备编码" align="center" prop="machineryCode" />
      <el-table-column label="设备名称" align="center" prop="machineryName" width="140"/> -->
      <el-table-column label="作业人员" align="center" prop="operator" />
      <!-- <el-table-column label="开始生产时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产时长" align="center" prop="duration" /> -->
      <el-table-column label="完成时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:pro:trace:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:pro:trace:remove']"
          >删除</el-button>
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

    <!-- 添加或修改生产追溯对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品物料编码" prop="itemCode">
          <el-input v-model="form.itemCode" placeholder="请输入产品物料编码" />
        </el-form-item>
        <el-form-item label="产品物料名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入产品物料名称" />
        </el-form-item>
        <el-form-item label="工序ID" prop="processId">
          <el-input v-model="form.processId" placeholder="请输入工序ID" />
        </el-form-item>
        <el-form-item label="工序编码" prop="processCode">
          <el-input v-model="form.processCode" placeholder="请输入工序编码" />
        </el-form-item>
        <el-form-item label="工序名称" prop="processName">
          <el-input v-model="form.processName" placeholder="请输入工序名称" />
        </el-form-item>
        <el-form-item label="设备ID" prop="machineryId">
          <el-input v-model="form.machineryId" placeholder="请输入设备ID" />
        </el-form-item>
        <el-form-item label="设备编码" prop="machineryCode">
          <el-input v-model="form.machineryCode" placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="设备名称" prop="machineryName">
          <el-input v-model="form.machineryName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="作业人员" prop="operator">
          <el-input v-model="form.operator" placeholder="请输入作业人员" />
        </el-form-item>
        <el-form-item label="开始生产时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择开始生产时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="生产时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入生产时长" />
        </el-form-item>
        <el-form-item label="完成生产时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择完成生产时间">
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
import { listTrace, getTrace, delTrace, addTrace, updateTrace } from "@/api/mes/pro/trace";
export default {
  name: "Trace",
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
      // 生产追溯表格数据
      traceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        itemCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        itemCode: [
          { required: true, message: "产品物料编码不能为空", trigger: "blur" }
        ],
        processId: [
          { required: true, message: "工序ID不能为空", trigger: "blur" }
        ],
        machineryId: [
          { required: true, message: "设备ID不能为空", trigger: "blur" }
        ],
        machineryCode: [
          { required: true, message: "设备编码不能为空", trigger: "blur" }
        ],
        machineryName: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询生产追溯列表 */
    getList() {
      this.loading = true;
      listTrace(this.queryParams).then(response => {
        this.traceList = response.rows;
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
        traceId: null,
        itemCode: null,
        itemName: null,
        processId: null,
        processCode: null,
        processName: null,
        machineryId: null,
        machineryCode: null,
        machineryName: null,
        operator: null,
        startTime: null,
        duration: null,
        endTime: null,
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
      this.ids = selection.map(item => item.traceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产追溯";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const traceId = row.traceId || this.ids
      getTrace(traceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产追溯";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.traceId != null) {
            updateTrace(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTrace(this.form).then(response => {
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
      const traceIds = row.traceId || this.ids;
      this.$modal.confirm('是否确认删除生产追溯编号为"' + traceIds + '"的数据项？').then(function() {
        return delTrace(traceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pro/trace/export', {
        ...this.queryParams
      }, `trace_${new Date().getTime()}.xlsx`)
    },

  
  }
};
</script>
