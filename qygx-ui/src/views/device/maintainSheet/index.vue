<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="设备" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保养人" prop="maintainerName">
        <el-input
          v-model="queryParams.maintainerName"
          placeholder="请输入保养人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['device:maintainSheet:add']"
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
          v-hasPermi="['device:maintainSheet:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleSubmit"
          v-hasPermi="['device:maintainSheet:edit']"
        >提交</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['device:maintainSheet:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="maintainSheetList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务编号" align="center" prop="maintainSheetId" />
      <!-- <el-table-column label="设备id" align="center" prop="deviceId" /> -->
      <el-table-column label="设备名称" align="center" prop="device.name" />
      <!-- <el-table-column
        label="生产厂商"
        align="center"
        prop="device.manufacturer"
      />
      <el-table-column label="产线" align="center" prop="device.location" /> -->
      <!-- <el-table-column label="保养人id" align="center" prop="maintainerId" /> -->
      <el-table-column
        label="保养人"
        align="center"
        prop="maintainer.nickName"
      />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.business_handle_state"
            :value="scope.row.status"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['device:maintainSheet:edit']"
            >保养</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改保养记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" label-width="100px" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备名称：" prop="name">
              {{ device.name }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备ip：" prop="ip">
              {{ device.ip }}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="安装地点：" prop="location">
              {{ device.location }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产厂商：" prop="manufacturer">
              {{ device.manufacturer }}
            </el-form-item>
          </el-col>
        </el-row>


        <el-divider content-position="center">保养记录明细信息</el-divider>
        <el-table
          :data="deviceMaintainDetailList"
          :row-class-name="rowDeviceMaintainDetailIndex"
          @selection-change="handleDeviceMaintainDetailSelectionChange"
          ref="deviceMaintainDetail"
        >
          <el-table-column
            label="序号"
            align="center"
            prop="index"
            width="50"
          />
          <el-table-column label="保养内容" prop="content" width="200" />
          <el-table-column label="标准" prop="standard" width="200" />
          <el-table-column label="状态" prop="isNormal" width="160">
            <template slot-scope="scope">
              <el-select v-model="scope.row.isNormal" placeholder="运行状态">
                <el-option
                  v-for="dict in dict.type.device_maintain_state"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="remark" width="150">
            <template slot-scope="scope">
              <el-input
                v-model="scope.row.remark"
                placeholder="请输入备注"
                type="textarea"
              />
            </template> </el-table-column
          >>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listMaintainSheet,
  getMaintainSheet,
  delMaintainSheet,
  addMaintainSheet,
  updateMaintainSheet,
  submitMaintainSheet
} from "@/api/device/maintainSheet";

export default {
  name: "MaintainSheet",
  dicts: ["business_handle_state", "device_maintain_state"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedDeviceMaintainDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 保养记录表格数据
      maintainSheetList: [],
      // 保养记录明细表格数据
      deviceMaintainDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sheetNumber: null,
        maintainerName: null,

        deviceName: null,
        status: null,
        endTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      device: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询保养记录列表 */
    getList() {
      this.loading = true;
      listMaintainSheet(this.queryParams).then((response) => {
        this.maintainSheetList = response.rows;
        if(this.maintainSheetList.length > 0 ){
          this.maintainSheetList = this.maintainSheetList.filter(item => item.status != "2");
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
        maintainSheetId: null,
        sheetNumber: null,
        maintainerId: null,
        status: "0",
        createTime: null,
        endTime: null,
        deviceId: null,
        createBy: null,
        updateBy: null,
        updateTime: null,
        remark: null,
      };
      this.deviceMaintainDetailList = [];
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
      this.ids = selection.map((item) => item.maintainSheetId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加保养记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const maintainSheetId = row.maintainSheetId || this.ids;
      getMaintainSheet(maintainSheetId).then((response) => {
        this.form = response.data;
        this.device = response.data.device;
        this.deviceMaintainDetailList = response.data.deviceMaintainDetailList;
        this.open = true;
        this.title = "修改保养记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.deviceMaintainDetailList = this.deviceMaintainDetailList;
          if (this.form.maintainSheetId != null) {
            updateMaintainSheet(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaintainSheet(this.form).then((response) => {
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
      const maintainSheetIds = row.maintainSheetId || this.ids;
      this.$modal
        .confirm(
          '是否确认删除保养记录编号为"' + maintainSheetIds + '"的数据项？'
        )
        .then(function () {
          return delMaintainSheet(maintainSheetIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },

     /** 提交按钮操作 */
     handleSubmit(row) {
      const maintainSheetIds = row.maintainSheetId || this.ids;
      this.$modal
        .confirm(
          '是否确认提交任务编号为"' + maintainSheetIds + '"的数据项？'
        )
        .then(function () {
          return submitMaintainSheet(maintainSheetIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("提交成功");
        })
        .catch(() => {});
    },


    /** 保养记录明细序号 */
    rowDeviceMaintainDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 保养记录明细添加按钮操作 */
    handleAddDeviceMaintainDetail() {
      let obj = {};
      obj.content = "";
      obj.standard = "";
      obj.isNormal = "";
      obj.remark = "";
      this.deviceMaintainDetailList.push(obj);
    },
    /** 保养记录明细删除按钮操作 */
    handleDeleteDeviceMaintainDetail() {
      if (this.checkedDeviceMaintainDetail.length == 0) {
        this.$modal.msgError("请先选择要删除的保养记录明细数据");
      } else {
        const deviceMaintainDetailList = this.deviceMaintainDetailList;
        const checkedDeviceMaintainDetail = this.checkedDeviceMaintainDetail;
        this.deviceMaintainDetailList = deviceMaintainDetailList.filter(
          function (item) {
            return checkedDeviceMaintainDetail.indexOf(item.index) == -1;
          }
        );
      }
    },
    /** 复选框选中数据 */
    handleDeviceMaintainDetailSelectionChange(selection) {
      this.checkedDeviceMaintainDetail = selection.map((item) => item.index);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "device/maintainSheet/export",
        {
          ...this.queryParams,
        },
        `maintainSheet_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
