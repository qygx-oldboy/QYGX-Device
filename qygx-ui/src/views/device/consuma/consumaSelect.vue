<template>
  <el-dialog
    title="备件选择"
    v-if="showFlag"
    :visible.sync="showFlag"
    :modal="false"
    width="80%"
    center
  >
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
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

    <el-table
      v-loading="loading"
      :data="consumacycleList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="备件编号" align="center" prop="consumaCode" />
      <el-table-column label="备件名称" align="center" prop="consumaName" />
      <el-table-column label="规格型号" align="center" prop="specs" />
      <!-- <el-table-column label="周期时长" align="center" prop="cycle" /> -->
      <!-- <el-table-column label="是否启用" align="center" prop="enableFlag">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.sys_yes_no"
            :value="scope.row.enableFlag"
          />
        </template>
      </el-table-column> -->
      <el-table-column label="备注" align="center" prop="remark" />
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="confirmSelect">确 定</el-button>
      <el-button @click="showFlag = false">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { listConsuma, getConsuma, delConsuma, addConsuma, updateConsuma } from "@/api/mes/csm/consuma";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "ConsumaSelect",
  dicts: ["sys_yes_no", "mes_machinery_status", "mes_device_type"],
  components: { Treeselect },
  data() {
    return {
      showFlag: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      selectedRows: [],
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
     
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        consumaCode: null,
        consumaName: null,
      },
    };
  },

  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listConsuma(this.queryParams).then((response) => {
        console.info(111);
        this.consumacycleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
      this.ids = selection.map((item) => item.machineryId);
      this.selectedRows = selection;
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    //确定选中
    confirmSelect() {
      if (this.ids == [] || this.ids.length == 0) {
        this.$notify({
          title: "提示",
          type: "warning",
          message: "请至少选择一条数据!",
        });
        return;
      }
      this.$emit("onSelected", this.selectedRows);
      this.showFlag = false;
    },
  },
};
</script>