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
      <el-form-item label="计划名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入计划名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保养人" prop="maintainerNickName">
        <el-input
          v-model="queryParams.maintainerNickName"
          placeholder="请输入保养人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保养等级" prop="level">
        <!-- <el-input
          v-model="queryParams.level"
          placeholder="请输入保养等级"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.level" placeholder="请选择保养等级">
          <el-option
            v-for="dict in dict.type.device_maintain_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['device:plan:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['device:plan:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['device:plan:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['device:plan:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="planList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="计划编号" align="center" prop="planId" />
      <el-table-column
        label="计划名称"
        align="center"
        prop="name"
        width="120"
      />
      <el-table-column label="设备类型" align="center" prop="deviceType" width="120">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.qygx_device_type"
            :value="scope.row.deviceType"
          />
        </template>
      </el-table-column>
      <el-table-column label="保养等级" align="center" prop="level" width="100">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.device_maintain_level"
            :value="scope.row.level"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="间隔天数"
        align="center"
        prop="intervalDays"
        width="100"
      />
      <el-table-column
        label="启用状态"
        align="center"
        prop="useState"
        width="100"
      >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.useState"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column
        label="保养负责人"
        align="center"
        prop="maintainerNickName"
        width="100"
      />

      <el-table-column
        label="首次保养计划时间"
        align="center"
        prop="firstMaintainTime"
        width="140"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.firstMaintainTime, "{y}-{m}-{d}")
          }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="最后一次保养时间"
        align="center"
        prop="lastMaintainTime"
        width="140"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.lastMaintainTime, "{y}-{m}-{d}")
          }}</span>
        </template>
      </el-table-column>


      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['device:plan:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['device:plan:remove']"
            >删除</el-button
          >

          <!-- <el-dropdown
            size="mini"
            @command="(command) => handleCommand(command, scope.row)"
          >
            <span class="el-dropdown-link">
              <i class="el-icon-d-arrow-right el-icon--right"></i>更多
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="handleAuthDevice" icon="el-icon-user"
                >分配设备</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown> -->
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

    <!-- 添加或修改保养计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="750px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="计划名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入计划名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备类型" prop="deviceType">
              <el-select v-model="form.deviceType" placeholder="请选择设备类型">
                <el-option
                  v-for="dict in dict.type.qygx_device_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="计划时间" prop="firstMaintainTime">
              <el-date-picker
                clearable
                v-model="form.firstMaintainTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择首次保养计划时间"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="间隔天数" prop="intervalDays">
              <el-input-number
                v-model="form.intervalDays"
                :min="1"
                :max="365"
                label="请输入间隔天数"
              />&nbsp;天
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人" prop="maintainerId">
              <el-select
                v-model="form.maintainerId"
                placeholder="请选择保养人"
                @change="changeValue($event)"
              >
                <el-option
                  v-for="item in maintainerOptions"
                  :key="item.userId"
                  :label="item.nickName"
                  :value="item.userId"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="保养等级" prop="level">
              <el-select v-model="form.level" placeholder="请选择保养等级">
                <el-option
                  v-for="dict in dict.type.device_maintain_level"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>




          
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="状态" prop="useState">
              <el-radio-group v-model="form.useState">
                <el-radio
                  v-for="dict in dict.type.sys_normal_disable"
                  :key="dict.value"
                  :label="dict.value"
                  :disabled="true"
                  >{{ dict.label }}</el-radio
                >
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input
                v-model="form.remark"
                type="textarea"
                placeholder="请输入内容"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="center">保养计划明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAddMaintainPlanDetail"
              >添加</el-button
            >
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="handleDeleteMaintainPlanDetail"
              >删除</el-button
            >
          </el-col>
        </el-row>
        <el-table
          :data="maintainPlanDetailList"
          :row-class-name="rowMaintainPlanDetailIndex"
          @selection-change="handleMaintainPlanDetailSelectionChange"
          ref="maintainPlanDetail"
        >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column
            label="序号"
            align="center"
            prop="index"
            width="50"
          />

          <el-table-column label="保养内容" prop="content" width="360">
            <template slot-scope="scope">
              <el-input
                v-model="scope.row.content"
                placeholder="请输入保养内容"
                type="textarea"
              />
            </template>
          </el-table-column>

          <el-table-column label="保养标准" prop="standard" width="240">
            <template slot-scope="scope">
              <el-input
                v-model="scope.row.standard"
                placeholder="请输入保养标准"
                type="textarea"
              />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 分配设备对话框 -->
    <!-- <el-dialog
      :title="title"
      :visible.sync="openDataScope"
      width="500px"
      append-to-body
    >
      <el-form :model="form" label-width="80px">
        <el-form-item label="设备列表">
          <el-checkbox
            v-model="deviceExpand"
            @change="handleCheckedTreeExpand($event, 'dept')"
            >展开/折叠</el-checkbox
          >
          <el-checkbox
            v-model="deptNodeAll"
            @change="handleCheckedTreeNodeAll($event, 'dept')"
            >全选/全不选</el-checkbox
          >
          <el-checkbox
            v-model="form.deptCheckStrictly"
            @change="handleCheckedTreeConnect($event, 'dept')"
            >父子联动</el-checkbox
          >
          <el-tree
            class="tree-border"
            :data="deviceOptions"
            show-checkbox
            default-expand-all
            ref="device"
            node-key="id"
            empty-text="加载中，请稍候"
            :props="defaultProps"
          ></el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDataScope">确 定</el-button>
        <el-button @click="cancelDataScope">取 消</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import {
  listPlan,
  getPlan,
  delPlan,
  addPlan,
  updatePlan,
  optionUser,
  changePlanStatus,
  deviceTreeselect,
} from "@/api/device/plan";

export default {
  name: "Plan",
  dicts: ["sys_normal_disable", "device_maintain_level", "qygx_device_type"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMaintainPlanDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 保养计划表格数据
      planList: [],
      // 保养计划明细表格数据
      maintainPlanDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 保养人选项
      maintainerOptions: [],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        person: null,
        level: null,
        name: null,
      },
      // 表单参数
      form: {},

      // 表单校验
      rules: {
        name: [
          { required: true, message: "计划名称不能为空", trigger: "blur" },
          {
            min: 2,
            max: 20,
            message: "计划名称长度必须介于 2 和 20 之间",
            trigger: "blur",
          },
        ],

        firstMaintainTime: [
          { required: true, message: "计划时间不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询保养计划列表 */
    getList() {
      this.loading = true;
      listPlan(this.queryParams).then((response) => {
        this.planList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 查询下拉框 */
    getTreeselect() {
      optionUser().then((response) => {
        this.maintainerOptions = response.data;
      });
    },

    // /** 查询设备树结构 */
    // getDeviceTreeselect() {
    //   deviceTreeselect().then((response) => {
    //     this.deviceOptions = response.data;
    //     console.info(this.deviceOptions);
    //   });
    // },

    //   /** 根据计划ID查询设备树结构 */
    // getPlanDeviceTreeselect(planId) {
    //   return planDeviceTreeselect(planId).then((response) => {
    //     this.deviceOptions = response.devices;
    //     return response;
    //   });
    // },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        planId: null,
        intervalDays: null,
        useState: "1",
        maintainerId: null,
        maintainerNickName: null,
        level: null,
        name: null,
        firstMaintainTime: null,
        lastMaintainTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
      };
      this.maintainPlanDetailList = [];
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
      this.ids = selection.map((item) => item.planId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect();
      this.open = true;
      this.title = "添加保养计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const planId = row.planId || this.ids;
      getPlan(planId).then((response) => {
        this.form = response.data;
        this.maintainPlanDetailList = response.data.maintainPlanDetailList;
        this.open = true;
        this.title = "修改保养计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.maintainPlanDetailList = this.maintainPlanDetailList;
          if (this.form.planId != null) {
            updatePlan(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlan(this.form).then((response) => {
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
      const planIds = row.planId || this.ids;
      this.$modal
        .confirm('是否确认删除保养计划编号为"' + planIds + '"的数据项？')
        .then(function () {
          return delPlan(planIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 保养计划明细序号 */
    rowMaintainPlanDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 保养计划明细添加按钮操作 */
    handleAddMaintainPlanDetail() {
      let obj = {};
      obj.content = "";
      obj.standard = "";
      this.maintainPlanDetailList.push(obj);
    },
    /** 保养计划明细删除按钮操作 */
    handleDeleteMaintainPlanDetail() {
      if (this.checkedMaintainPlanDetail.length == 0) {
        this.$modal.msgError("请先选择要删除的保养计划明细数据");
      } else {
        const maintainPlanDetailList = this.maintainPlanDetailList;
        const checkedMaintainPlanDetail = this.checkedMaintainPlanDetail;
        this.maintainPlanDetailList = maintainPlanDetailList.filter(function (
          item
        ) {
          return checkedMaintainPlanDetail.indexOf(item.index) == -1;
        });
      }
    },
    /** 复选框选中数据 */
    handleMaintainPlanDetailSelectionChange(selection) {
      this.checkedMaintainPlanDetail = selection.map((item) => item.index);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "device/plan/export",
        {
          ...this.queryParams,
        },
        `plan_${new Date().getTime()}.xlsx`
      );
    },

    // 计划状态修改
    handleStatusChange(row) {
      console.info(row);
      let text = row.useState === "0" ? "启用" : "停用";
      this.$modal
        .confirm('确认要"' + text + '""' + row.name + '"计划吗？')
        .then(function () {
          return changePlanStatus(row.planId, row.useState,row.deviceType,row.level);
        })
        .then(() => {
          this.$modal.msgSuccess(text + "成功");
        })
        .catch(function () {
          row.useState = row.useState === "0" ? "1" : "0";
        });
    },

    //下拉框传递多个参数id
    changeValue(event) {
      console.info(event);
      this.maintainerId = event.userId;
      this.maintainerNickName = event.nickName;
    },

    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        // case "handleDataScope":
        //   this.handleDataScope(row);
        //   break;
        case "handleAuthDevice":
          this.handleAuthDevice(row);
          break;
        default:
          break;
      }
    },

    /** 分配数据权限操作 */
    // handleAuthDevice(row) {
    //   this.openDataScope = true;
    //   this.getDeviceTreeselect();
    //   this.title = "分配设备";
    // },

    // 树权限（展开/折叠）
    // handleCheckedTreeExpand(value, type) {
    //   // if (type == "menu") {
    //   //   let treeList = this.menuOptions;
    //   //   for (let i = 0; i < treeList.length; i++) {
    //   //     this.$refs.menu.store.nodesMap[treeList[i].id].expanded = value;
    //   //   }
    //   // }
    // },
  },
};
</script>
