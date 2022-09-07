<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="备件编号" prop="consumaCode">
        <el-input
          v-model="queryParams.consumaCode"
          placeholder="请输入备件编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mes:csm:consumacycle:add']"
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
          v-hasPermi="['mes:csm:consumacycle:edit']"
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
          v-hasPermi="['mes:csm:consumacycle:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:csm:consumacycle:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="consumacycleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="备件ID" align="center" prop="consumaId" />
      <el-table-column label="备件编号" align="center" prop="consumaCode" />
      <el-table-column label="备件名称" align="center" prop="consumaName" />
      <el-table-column label="规格型号" align="center" prop="specs" />
      <el-table-column label="周期时长" align="center" prop="cycle" />
      <el-table-column label="是否启用" align="center" prop="enableFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.enableFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:csm:consumacycle:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:csm:consumacycle:remove']"
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

    <!-- 添加或修改耗材周期对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
       <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="备件编号" prop="consumaCode">
          <el-input v-model="form.consumaCode" placeholder="请输入备件编号" />
        </el-form-item>
        <el-form-item label="备件名称" prop="consumaName">
          <el-input v-model="form.consumaName" placeholder="请输入备件名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="specs">
          <el-input v-model="form.specs" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="周期时长" prop="cycle">
          <el-input-number v-model="form.cycle" placeholder="请输入周期时长" /> 小时
        </el-form-item>
        <el-form-item label="是否启用">
          <el-radio-group v-model="form.enableFlag">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listConsumacycle, getConsumacycle, delConsumacycle, addConsumacycle, updateConsumacycle } from "@/api/mes/csm/consumacycle";

export default {
  name: "Consumacycle",
  dicts: ['sys_yes_no'],
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
      // 耗材周期表格数据
      consumacycleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        consumaCode: null,
        consumaName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        consumaCode: [
          { required: true, message: "备件编号不能为空", trigger: "blur" }
        ],
        consumaName: [
          { required: true, message: "备件名称不能为空", trigger: "blur" }
        ],
        enableFlag: [
          { required: true, message: "是否启用不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询耗材周期列表 */
    getList() {
      this.loading = true;
      listConsumacycle(this.queryParams).then(response => {
        this.consumacycleList = response.rows;
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
        consumaId: null,
        consumaCode: null,
        consumaName: null,
        specs: null,
        cycle: null,
        enableFlag: "Y",
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
      this.ids = selection.map(item => item.consumaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加耗材周期";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const consumaId = row.consumaId || this.ids
      getConsumacycle(consumaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改耗材周期";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.consumaId != null) {
            updateConsumacycle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsumacycle(this.form).then(response => {
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
      const consumaIds = row.consumaId || this.ids;
      this.$modal.confirm('是否确认删除耗材周期编号为"' + consumaIds + '"的数据项？').then(function() {
        return delConsumacycle(consumaIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes/csm/consumacycle/export', {
        ...this.queryParams
      }, `consumacycle_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
