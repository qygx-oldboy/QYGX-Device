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
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input
          v-model="queryParams.deviceCode"
          placeholder="请输入设备编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备ip" prop="ip">
        <el-input
          v-model="queryParams.ip"
          placeholder="请输入设备ip"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格" prop="standard">
        <el-input
          v-model="queryParams.standard"
          placeholder="请输入规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-select
          v-model="queryParams.deviceType"
          placeholder="请选择设备类型"
          clearable
        >
          <el-option
            v-for="dict in dict.type.mes_device_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="生产厂商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入生产厂商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="安装地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入安装地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资产编号" prop="assetCode">
        <el-input
          v-model="queryParams.assetCode"
          placeholder="请输入资产编号"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['device:archives:add']"
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
          v-hasPermi="['device:archives:edit']"
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
          v-hasPermi="['device:archives:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['product:archives:import']"
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
          v-hasPermi="['device:archives:export']"
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
      :data="archivesList"
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
      </el-table-column>
      <el-table-column label="设备ip" align="center" prop="ip" />
      <el-table-column label="规格" align="center" prop="standard" />
      <el-table-column label="生产厂商" align="center" prop="manufacturer" />
      <el-table-column label="设备状态" align="center" prop="status" />
      <el-table-column label="安装地点" align="center" prop="location" />
      <el-table-column
        label="操作"
        align="center"
        width="200px"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-shop"
            @click="handleConsuma(scope.row.deviceId)"
            v-hasPermi="['device:archives:edit']"
          >备件</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['device:archives:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['device:archives:remove']"
            >删除</el-button
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

    <!-- 添加或修改设备档案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="设备编码" prop="deviceCode">
          <el-input v-model="form.deviceCode" placeholder="请输入设备编码" />
        </el-form-item> -->
        <el-row>
          <el-col :span="16">
            <el-form-item label="设备编码" prop="deviceCode">
              <el-input
                v-model="form.deviceCode"
                readonly="readonly"
                maxlength="64"
                v-if="optType == 'view'"
              />
              <el-input
                v-model="form.deviceCode"
                placeholder="请输入设备编码"
                maxlength="64"
                v-else
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label-width="80">
              <el-switch
                v-model="autoGenFlag"
                active-color="#13ce66"
                active-text="自动生成"
                @change="handleAutoGenChange(autoGenFlag)"
                v-if="optType != 'view'"
              >
              </el-switch>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="设备名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="资产编号" prop="assetCode">
          <el-input v-model="form.assetCode" placeholder="请输入资产编号" />
        </el-form-item>
        <el-form-item label="设备类型" prop="deviceType">
          <el-select v-model="form.deviceType" placeholder="请选择设备类型">
            <el-option
              v-for="dict in dict.type.mes_device_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="规格" prop="standard">
          <el-input v-model="form.standard" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="设备ip" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入设备ip" />
        </el-form-item>
        <el-form-item label="生产厂商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入生产厂商" />
        </el-form-item>
        <el-form-item label="安装地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入安装地点" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px">
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
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox
            v-model="upload.updateSupport"
          />是否更新已经存在的用户数据
          <el-link type="info" style="font-size: 12px" @click="importTemplate"
            >下载模板</el-link
          >
        </div>
        <div class="el-upload__tip" style="color: red" slot="tip">
          提示：仅允许导入“xls”或“xlsx”格式文件！
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
  listArchives,
  getArchives,
  delArchives,
  addArchives,
  updateArchives,
} from "@/api/device/archives";
import { genCode } from "@/api/system/autocode/rule";
import { getToken } from "@/utils/auth";

export default {
  name: "Archives",
  dicts: ["mes_device_type"],
  data() {
    return {
      //自动生成编码
      autoGenFlag: false,
      optType: undefined,
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
      // 设备档案表格数据
      archivesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
        assetCode: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deviceCode: [
          { required: true, message: "设备编码不能为空", trigger: "blur" },
          { max: 64, message: "设备编码长度必须小于64个字符", trigger: "blur" },
        ],
        name: [
          { required: true, message: "设备名称不能为空", trigger: "blur" },
        ],
        deviceType: [
          { required: true, message: "设备分类不能为空", trigger: "blur" },
        ],
      },
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/device/archives/importData",
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备档案列表 */
    getList() {
      this.loading = true;
      listArchives(this.queryParams).then((response) => {
        this.archivesList = response.rows;
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
        deviceId: null,
        deviceCode: null,
        ip: null,
        name: null,
        standard: null,
        deviceType: null,
        manufacturer: null,
        status: "0",
        location: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        assetCode: null
      };
      this.autoGenFlag = false;
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
      this.ids = selection.map((item) => item.deviceId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备档案";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deviceId = row.deviceId || this.ids;
      getArchives(deviceId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备档案";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.deviceId != null) {
            updateArchives(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArchives(this.form).then((response) => {
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
      const deviceIds = row.deviceId || this.ids;
      this.$modal
        .confirm('是否确认删除设备档案编号为"' + deviceIds + '"的数据项？')
        .then(function () {
          return delArchives(deviceIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },

    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "数据导入";
      this.upload.open = true;
    },

    /** 下载模板操作 */
    importTemplate() {
      this.download(
        "device/archives/importTemplate",
        {},
        `archives_template_${new Date().getTime()}.xlsx`
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
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "device/archives/export",
        {
          ...this.queryParams,
        },
        `archives_${new Date().getTime()}.xlsx`
      );
    },

    //自动生成编码
    handleAutoGenChange(autoGenFlag) {
      debugger;
      if (autoGenFlag) {
        genCode("MACHINERY_CODE").then((response) => {
          this.form.deviceCode = response;
        });
      } else {
        this.form.deviceCode = null;
      }
    },

    //备件
    handleConsuma(deviceId){
      //this.$router.push({ path: '/mes/wm/location/index', query: { warehouseId: warehouseId || 0 ,optType: this.optType} })
      this.$router.push({ path: '/mes/csm/consumause/index'})
    },
  },
};
</script>
