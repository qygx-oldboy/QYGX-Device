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
      <el-form-item label="批次号" prop="qrCode">
        <el-input
          v-model="queryParams.qrCode"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产编号" prop="productCode">
        <el-input
          v-model="queryParams.productCode"
          placeholder="请输入生产编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="材料" prop="materialQuality">
        <el-input
          v-model="queryParams.materialQuality"
          placeholder="请输入材料"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检验员" prop="inspector">
        <el-input
          v-model="queryParams.inspector"
          placeholder="请输入检验员"
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
      ref="tables"
      v-loading="loading"
      :data="inspectList"
      @selection-change="handleSelectionChange"
      :default-sort="defaultSort"
      @sort-change="handleSortChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="批次号"
        width="180"
        align="center"
        prop="qrCode"
      />
      <el-table-column label="生产编号" align="center" prop="productCode" />
      <el-table-column label="检验数" align="center" prop="inspectedNum" />
      <el-table-column label="合格数" align="center" prop="okNum" />
      <el-table-column label="不良数" align="center" prop="ngNum" />
      <el-table-column label="生产类型" align="center" prop="productType" />
      <!-- <el-table-column label="物料类型" align="center" prop="materialType" /> -->
      <el-table-column
        label="材料"
        width="110"
        align="center"
        prop="materialQuality"
      />

      <el-table-column label="检验员" align="center" prop="inspector" />

      <el-table-column prop="status" label="状态" width="80">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.business_handle_state"
            :value="scope.row.status"
          />
        </template>
      </el-table-column>

      <el-table-column
        label="开始时间"
        align="center"
        prop="beginTime"
        sortable="custom"
        :sort-orders="['descending', 'ascending']"
        width="180"
      ></el-table-column>
      <!-- <el-table-column
        label="开始时间"
        align="center"
        prop="beginTime"
        width="180"
        sortable="custom"
        :sort-orders="['descending', 'ascending']"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.beginTime }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column
        label="结束时间"
        align="center"
        prop="endTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.endTime }}</span>
        </template>
      </el-table-column> -->
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
            @click="handleView(scope.row)"
            v-hasPermi="['product:inspect:edit']"
            >分析改善</el-button
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





<el-dialog title="分析改善" :visible.sync="open" width="780px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      
         <el-col :span="24">
            <el-form-item label="内容" prop="result">
              <!-- <editor v-model="form.result" :min-height="192"/> -->

                <el-input
                type="textarea"
                :autosize="{ minRows: 16, maxRows: 32}"
                placeholder="请输入内容"
                height="192"
                v-model="form.result">
                </el-input>

            </el-form-item>
            
          </el-col>
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
  listInspect,
  getInspect,
  delInspect,
  addInspect,
  updateInspect,
} from "@/api/product/girdQuality";
import { getToken } from "@/utils/auth";

export default {
  name: "GirdException",
  dicts: ["business_handle_state"],
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
      // inspect表格数据
      inspectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 默认排序
      defaultSort: { prop: "beginTime", order: "descending" },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        qrCode: null,
        productCode: null,
        productType: null,
        materialType: null,
        materialQuality: null,
        inspector: null,
        beginTime: null,
        okNum: 0,
      },
      // 表单参数
      form: {
        result: undefined
      },
      // 表单校验
      rules: {
         result: [
          { required: true, message: "分析建议不能为空", trigger: "blur" },
          { min: 24, max: 80, message: "长度在 24 到 80 个字符", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询inspect列表 */
    getList() {
      this.loading = true;
      listInspect(this.queryParams).then((response) => {
        this.inspectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.$refs.tables.sort(this.defaultSort.prop, this.defaultSort.order);
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 排序触发事件 */
    handleSortChange(column, prop, order) {
      this.queryParams.orderByColumn = column.prop;
      this.queryParams.isAsc = column.order;
      this.getList();
    },

    /** 操作 */
    handleView(row) {
      
       const id = row.id || this.ids
        getInspect(id).then(response => {
            console.info(response);
            this.form = response.data;
            this.open = true;
           
        });
     
    },

     /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateInspect(this.form).then(response => {
              this.$modal.msgSuccess("处理成功");
              this.open = false;
              this.getList();
            });
        }
      });
    },
    // 表单重置
    reset() {
      this.form = {
        result: undefined
      };
      this.resetForm("form");
    },
   
  },
};
</script>
