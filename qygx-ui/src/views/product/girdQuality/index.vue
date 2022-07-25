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
      <!-- <el-form-item label="开始时间" prop="beginTime">
        <el-date-picker
          clearable
          v-model="queryParams.beginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始时间"
        >
        </el-date-picker>
      </el-form-item> -->
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
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['product:inspect:import']"
          >导入</el-button
        >
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['product:inspect:export']"
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
      :data="inspectList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column
        label="批次号"
        width="165"
        align="center"
        prop="qrCode"
      />
      <el-table-column
        label="生产编号"
        width="110"
        align="center"
        prop="productCode"
      />
      <el-table-column label="检验数" align="center" prop="inspectedNum" />
      <el-table-column label="合格数" align="center" prop="okNum" />
      <el-table-column label="不良数" align="center" prop="ngNum" />
      <el-table-column label="生产类型" align="center" prop="productType" />
      <el-table-column label="物料类型" align="center" prop="materialType" />
      <el-table-column
        label="材料"
        width="110"
        align="center"
        prop="materialQuality"
      />
      <el-table-column
        label="标准成盘数"
        width="110"
        align="center"
        prop="quantityStd"
      />
      <el-table-column label="检验员" align="center" prop="inspector" />
      <el-table-column
        label="开始时间"
        align="center"
        prop="beginTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.beginTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="结束时间"
        align="center"
        prop="endTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.endTime }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column
        label="检验用时"
        align="center"
        prop="runTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.runTime }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="良率" align="center" prop="okPercent" />
      <el-table-column label="不良率" align="center" prop="ngPercent" /> -->
      <el-table-column label="材质不良" align="center" prop="ng1" />
      <el-table-column label="失园" align="center" prop="ng2" />
      <el-table-column label="内裂" align="center" prop="ng3" />
      <el-table-column label="坑点" align="center" prop="ng4" />
      <el-table-column label="尺寸不良" align="center" prop="ng5" />
      <el-table-column label="发雾" align="center" prop="ng6" />
      <el-table-column label="伤痕" align="center" prop="ng7" />
      <el-table-column label="印渍" align="center" prop="ng8" />
      <el-table-column label="不洁" align="center" prop="ng9" />
      <el-table-column label="其他" align="center" prop="ng10" />
      <!-- <el-table-column label="处理结果" align="center" prop="result" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:inspect:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:inspect:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 用户导入对话框 -->
    <el-dialog
      :title="upload.title"
      :visible.sync="upload.open"
      width="400px"
      append-to-body
    >
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <!-- <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的数据
          </div> -->
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link
            type="primary"
            :underline="false"
            style="font-size: 12px; vertical-align: baseline"
            @click="importTemplate"
            >下载模板</el-link
          >
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
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
  name: "Inspect",
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
      },
      // 数据导入参数
      upload: {
        // 是否显示弹出层（数据导入）
        open: false,
        // 弹出层标题（数据导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/product/report/importData",
      },
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
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "product/report/export",
        {
          ...this.queryParams,
        },
        `inspect_${new Date().getTime()}.xlsx`
      );
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "数据导入";
      this.upload.open = true;
    },

    /** 下载模板操作 */
    importTemplate() {
      this.download(
        "product/report/importTemplate",
        {},
        `inspect_template_${new Date().getTime()}.xlsx`
      );
    },

    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(
        "<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" +
          response.msg +
          "</div>",
        "导入结果",
        { dangerouslyUseHTMLString: true }
      );
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
  },
};
</script>
