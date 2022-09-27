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
      <el-form-item label="规格型号" prop="specs">
        <el-input
          v-model="queryParams.specs"
          placeholder="请输入规格型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更换人" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入更换人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="更换时间" prop="replaceTime">
        <el-date-picker clearable
          v-model="queryParams.replaceTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更换时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="登录时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>


      <el-form-item label="备件批次" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入备件批次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编号" prop="deviceCode">
        <el-input
          v-model="queryParams.deviceCode"
          placeholder="请输入设备编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mes:csm:repalceRecord:add']"
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
          v-hasPermi="['mes:csm:repalceRecord:edit']"
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
          v-hasPermi="['mes:csm:repalceRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:csm:repalceRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading" :data="repalceRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="recordId" />
      <el-table-column label="备件编号" align="center" prop="consumaCode" />
      <el-table-column label="备件名称" align="center" prop="consumaName" />
      <el-table-column label="规格型号" align="center" prop="specs" />
      <el-table-column label="更换人" align="center" prop="nickName" />
      
      <el-table-column label="备件批次" align="center" prop="batchNo" />
      <!-- <el-table-column label="备注" align="center" prop="remark" /> -->
      <el-table-column label="设备编号" align="center" prop="deviceCode" />
      <el-table-column label="设备名称" align="center" prop="deviceName" />
      <el-table-column label="更换时间" align="center" prop="replaceTime" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.replaceTime }}</span>
        </template>
      </el-table-column>
       <!--<el-table-column label="预留字段3" align="center" prop="attr3" />
      <el-table-column label="预留字段4" align="center" prop="attr4" /> -->
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:csm:repalceRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:csm:repalceRecord:remove']"
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

    <!-- 添加或修改备件更换记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="备件编号" prop="consumaCode">
          <el-input v-model="form.consumaCode" placeholder="请输入备件编号" />
        </el-form-item>
        <el-form-item label="备件名称" prop="consumaName">
          <el-input v-model="form.consumaName" placeholder="请输入备件名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="specs">
          <el-input v-model="form.specs" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="更换时间" prop="replaceTime">
          <el-date-picker clearable
            v-model="form.replaceTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更换时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备件批次" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入备件批次" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="预留字段1" prop="attr1">
          <el-input v-model="form.attr1" placeholder="请输入预留字段1" />
        </el-form-item>
        <el-form-item label="预留字段2" prop="attr2">
          <el-input v-model="form.attr2" placeholder="请输入预留字段2" />
        </el-form-item>
        <el-form-item label="预留字段3" prop="attr3">
          <el-input v-model="form.attr3" placeholder="请输入预留字段3" />
        </el-form-item>
        <el-form-item label="预留字段4" prop="attr4">
          <el-input v-model="form.attr4" placeholder="请输入预留字段4" />
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
import { listRepalceRecord, getRepalceRecord, delRepalceRecord, addRepalceRecord, updateRepalceRecord } from "@/api/mes/csm/repalceRecord";

export default {
  name: "RepalceRecord",
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
      // 备件更换记录表格数据
      repalceRecordList: [],
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
        specs: null,
        nickName: null,
        replaceTime: null,
        batchNo: null,
        deviceCode: null,
        deviceName: null,
        attr3: null,
        attr4: null,
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
      },
      // 日期范围
      dateRange: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询备件更换记录列表 */
    getList() {
      this.loading = true;
      listRepalceRecord(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.repalceRecordList = response.rows;
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
        consumaCode: null,
        consumaName: null,
        specs: null,
        nickName: null,
        replaceTime: null,
        batchNo: null,
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
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加备件更换记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recordId = row.recordId || this.ids
      getRepalceRecord(recordId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改备件更换记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateRepalceRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRepalceRecord(this.form).then(response => {
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
      const recordIds = row.recordId || this.ids;
      this.$modal.confirm('是否确认删除备件更换记录编号为"' + recordIds + '"的数据项？').then(function() {
        return delRepalceRecord(recordIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes:csm/repalceRecord/export', {
        ...this.queryParams
      }, `repalceRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
