<template>
  <el-dialog
    title="设备选择"
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
      
          <el-form-item label="设备编码" prop="machineryCode">
            <el-input
              v-model="queryParams.machineryCode"
              placeholder="请输入设备编码"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="设备名称" prop="machineryName">
            <el-input
              v-model="queryParams.machineryName"
              placeholder="请输入设备名称"
              clearable
              style="width: 240px"
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
          :data="machineryList"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55" align="center" />
          <!-- <el-table-column label="设备id" align="center" prop="deviceId" /> -->
          <el-table-column label="设备编码" align="center" prop="deviceCode">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['device:archives:edit']"
                >{{ scope.row.deviceCode }}</el-button
              >
            </template>
          </el-table-column>
          <el-table-column label="设备名称" align="center" prop="name" />
          <el-table-column label="资产编号" align="center" prop="assetCode" />
          <el-table-column label="设备类型" align="center" prop="deviceType">
            <template slot-scope="scope">
              <dict-tag
                :options="dict.type.mes_device_type"
                :value="scope.row.deviceType"
              />
            </template>
          </el-table-column> -->
          <el-table-column label="设备ip" align="center" prop="ip" />
          <el-table-column label="规格" align="center" prop="standard" />
          <el-table-column
            label="生产厂商"
            align="center"
            prop="manufacturer"
          /> 
          <el-table-column label="设备状态" align="center" prop="status" />
          <el-table-column label="安装地点" align="center" prop="location" /> 
        
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
import {
  listArchives,
  getArchives,
  delArchives,
  addArchives,
  updateArchives,
} from "@/api/device/archives";

import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
//import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "MachinerySelect",
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
      // 物料产品表格数据
      machineryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //自动生成物料编码标识
      autoGenFlag: false,
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceCode: null,
        ip: null,
        name: null,
        standard: null,
        deviceType: null,
        manufacturer: null,
        status: null,
        location: null,
        assetCode: null,
      },
    };
  },

  created() {
    this.getList();
  },
  methods: {
    /** 查询物料编码列表 */
    getList() {
      this.loading = true;
      listArchives(this.queryParams).then((response) => {
        this.machineryList = response.rows;
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