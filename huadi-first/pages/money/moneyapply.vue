<template>
  <div style="margin-left:30%">
    <el-form ref="form1" :model="form1" label-width="80px">
      <el-form-item label="申请类型">
        <el-select v-model="form1.type" placeholder="请选择">
          <el-option label="饮食类" value="饮食类"></el-option>
          <el-option label="交通类" value="交通类"></el-option>
          <el-option label="办公类" value="办公类"></el-option>
          <el-option label="其它" value="其它"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="金额">
        <el-input-number
          :min="0"
          v-model="form1.money"
          controls-position="right"
          placeholder="请输入金额"
        />
      </el-form-item>

      <el-form-item label="详细说明">
        <el-input type="textarea" v-model="form1.detail" style="width: 400px"></el-input>
      </el-form-item>

      <el-form-item label="上传证明材料">
        <el-upload
          class="upload-demo"
          drag
          action="http://192.168.110.149:7002/manageoss/file/upload/huadi-test"
          :multiple="false"
          :show-file-list="false"
          :auto-upload="true"
          :on-success="success"
          :on-progress="wait"
          :on-error="error"
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div class="el-upload__tip" slot="tip">上传各种类型的文件，且不超过1M</div>
          <div class="el-upload__tip" slot="tip" >上传结果：{{result}}</div>
        </el-upload>
      </el-form-item>

      <el-form-item style="margin-left:100px">
        <el-button type="primary" @click="onSubmit">提交申请</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import moneyApi from "@/api/money";
export default {
  data() {
    return {
      result: "",
      form1: {
        type: "",
        money: "",
        detail: "",
        sourceurl: "",
      },
    };
  },
  created() {},
  methods: {
    success(response, file, fileList) {
      this.result = "上传完成";
      this.form1.sourceurl = response.data.url;
      this.$message.success("上传成功");
    },
    wait(event, file, fileList) {
      this.result = "上传中";
    },
    error(err, file, fileList) {
      this.result = "上传失败";
      this.$message.success(err);
    },
    onSubmit() {
      this.$confirm("是否确认提交?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          moneyApi.moneyapply(this.form1).then((response) => {
            this.$message.success(response.message);
            if(response.success){
            setTimeout(function () {
              window.location.href = "/money/myapply"; //1秒后刷新页面
            }, "1000");
          }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    //下面声明方法结束
  },
};
</script>