<template>
  <div class="navbar">
    <hamburger
      id="hamburger-container"
      :is-active="sidebar.opened"
      class="hamburger-container"
      @toggleClick="toggleSideBar"
    />

    <breadcrumb
      id="breadcrumb-container"
      class="breadcrumb-container"
      v-if="!topNav"
    />
    <top-nav id="topmenu-container" class="topmenu-container" v-if="topNav" />

    <div class="right-menu">
      <template v-if="device !== 'mobile'">
        <search id="header-search" class="right-menu-item" />

        <!-- <el-tooltip content="源码地址" effect="dark" placement="bottom">
          <ruo-yi-git id="ruoyi-git" class="right-menu-item hover-effect" />
        </el-tooltip>

        <el-tooltip content="文档地址" effect="dark" placement="bottom">
          <ruo-yi-doc id="ruoyi-doc" class="right-menu-item hover-effect" />
        </el-tooltip> -->

        <screenfull id="screenfull" class="right-menu-item hover-effect" />

        <el-tooltip content="布局大小" effect="dark" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect" />
        </el-tooltip>
      </template>

      <el-dropdown
        class="avatar-container right-menu-item hover-effect"
        trigger="click"
      >
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar" />
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item @click.native="setting = true">
            <span>布局设置</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Breadcrumb from "@/components/Breadcrumb";
import TopNav from "@/components/TopNav";
import Hamburger from "@/components/Hamburger";
import Screenfull from "@/components/Screenfull";
import SizeSelect from "@/components/SizeSelect";
import Search from "@/components/HeaderSearch";
import RuoYiGit from "@/components/RuoYi/Git";
import RuoYiDoc from "@/components/RuoYi/Doc";
import { Notification } from "element-ui";
import moment from "moment";
import axios from "axios";

export default {
  components: {
    Breadcrumb,
    TopNav,
    Hamburger,
    Screenfull,
    SizeSelect,
    Search,
    RuoYiGit,
    RuoYiDoc,
  },
  computed: {
    ...mapGetters(["sidebar", "avatar", "device"]),
    setting: {
      get() {
        return this.$store.state.settings.showSettings;
      },
      set(val) {
        this.$store.dispatch("settings/changeSetting", {
          key: "showSettings",
          value: val,
        });
      },
    },
    topNav: {
      get() {
        return this.$store.state.settings.topNav;
      },
    },
  },

  data() {
    return {
      //url: "ws://" + process.env.VUE_APP_BASE_SOCKET + "/webSocket",
      url: process.env.VUE_APP_BASE_SOCKET,
      message: "",
      text_content: "",
      ws: null,
    };
  },

  //若依框架嵌入websocket
  mounted() {
    // vue 3.0以上跳转多次报错的解决方法
    // const originalPush = Router.prototype.push;
    // Router.prototype.push = function push(location) {
    //   return originalPush.call(this, location).catch((err) => err);
    // };
   
    // const wsuri = "ws://106.14.139.90:8080/webSocket";
    const wsuri = "ws://127.0.0.1:8080/webSocket";
    this.ws = new WebSocket(wsuri);
    const self = this;
    this.ws.onopen = function (event) {
      self.text_content = self.text_content + "已经打开连接!" + "\n";
    };
    this.ws.onmessage = function (event) {

      self.text_content = event.data + "\n";


      if (event.data.indexOf("qrCode") > -1) {
        var messageBody = JSON.parse(event.data);


        const rates = (messageBody.okPercent * 100).toFixed(2) + '%'; //转百分比
        Notification.error({
          title: "良率异常告警",
          dangerouslyUseHTMLString: true,
          message:
            "<strong>时间：" +
            moment(new Date(messageBody.beginTime)).format(
              "YYYY-MM-DD HH:mm:ss"
            ) +
            " " +
            "  <br/> <i> 批次：" +
            messageBody.qrCode +
             "  <br/> <i> 良率：" +
            rates +
            "  <br/> <i> 检验员：" +
            messageBody.inspector +
            "</i> </strong>",
          duration: 0,
          position: "bottom-right",
          onClick: function () {
            self.warnDetailByWarnid(); //自定义回调,message为传的参数
            // 点击跳转的页面
          },
        });
      }

      // 判断是推动预警消息的时候
      // if (event.data.indexOf("warnId") > -1) {
      //   var messageBody = JSON.parse(event.data);
      //   // 预警消息包含预警id的时候
      //   if (messageBody.warnId != undefined) {
      //     Notification.error({
      //       title: messageBody.caseName + "(" + messageBody.menuName + ")",
      //       dangerouslyUseHTMLString: true,
      //       message:
      //         "<strong>时间：" +
      //         moment(new Date(messageBody.warnDate)).format(
      //           "YYYY-MM-DD HH:mm:ss"
      //         ) +
      //         " " +
      //         messageBody.warnMsg +
      //         "  <br/> <i> 处理方案：" +
      //         messageBody.caseWarndesc +
      //         "</i> </strong>",
      //       duration: 0,
      //       position: "bottom-right",
      //       onClick: function () {
      //         self.warnDetailByWarnid(messageBody.warnId); //自定义回调,message为传的参数
      //         // 点击跳转的页面
      //       },
      //     });

      //   }
      // }
    };
    this.ws.onclose = function (event) {
      self.text_content = self.text_content + "已经关闭连接!" + "\n";
    };
  },

  methods: {
    toggleSideBar() {
      this.$store.dispatch("app/toggleSideBar");
    },
    async logout() {
      this.$confirm("确定注销并退出系统吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$store.dispatch("LogOut").then(() => {
            location.href = "/index";
          });
        })
        .catch(() => {});
    },

    warnDetailByWarnid() {
      // 跳转预警详情页面
      this.$router.push({
        path: "/product/girdException",
        query: {
          // warnid: warnid,
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .topmenu-container {
    position: absolute;
    left: 50px;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
