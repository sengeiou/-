<template>
  <div>
    <el-form ref="form1" :model="form1" label-width="80px">
      
 <el-row>
                <el-col :span="8">

      <el-form-item label="姓名">
        <el-input v-model="form1.name"></el-input>
      </el-form-item>

 </el-col>
                <el-col :span="8">

      <el-form-item label="性别">
        <el-select v-model="form1.sex" placeholder="请选择性别">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>

       </el-col>
                <el-col :span="8">

      <el-form-item label="年龄">
        <el-input-number
          :min="0"
          v-model="form1.age"
          controls-position="right"
          placeholder="年龄必须为整数"
        />
      </el-form-item>

 </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">


      <el-form-item label="身高">
        <el-input-number :min="0" v-model="form1.height" controls-position="right" />
      </el-form-item>

 </el-col>
                <el-col :span="6">


      <el-form-item label="体重">
        <el-input v-model="form1.weight" style="width: 180px"></el-input>kg
      </el-form-item>

 </el-col>
                <el-col :span="6">



      <el-form-item label="视力">
        <el-input v-model="form1.eyes" style="width: 180px"></el-input>
      </el-form-item>

 </el-col>
                <el-col :span="6">


      <el-form-item label="民族">
        <el-input v-model="form1.nation" style="width: 180px"></el-input>
      </el-form-item>

 </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">

                        <el-form-item label="手机号">
        <el-input v-model="form1.phone" style="width: 250px"></el-input>
      </el-form-item>

       </el-col>
                <el-col :span="8">


      <el-form-item label="身份证号">
        <el-input v-model="form1.identitycard" style="width: 280px"></el-input>
      </el-form-item>

       </el-col>
                <el-col :span="8">

            <el-form-item label="政治面貌">
        <el-input v-model="form1.politicaloutlook" style="width: 180px"></el-input>
      </el-form-item>

     </el-col>
            </el-row>

      <el-form-item label="婚姻状况">
        <el-input type="textarea" v-model="form1.ismarry"></el-input>
      </el-form-item>
      <el-form-item label="出生地">
        <el-input type="textarea" v-model="form1.bornplace"></el-input>
      </el-form-item>


      <el-form-item label="现居住的">
        <el-input type="textarea" v-model="form1.nowplace"></el-input>
      </el-form-item>


      <el-form-item label="健康状况">
        <el-input type="textarea" v-model="form1.healthy"></el-input>
      </el-form-item>

<el-row>
                <el-col :span="8">

      <el-form-item label="紧急联系人" label-width="100px">
        <el-input v-model="form1.emergencypeople" style="width: 180px"></el-input>
      </el-form-item>


     </el-col>
                <el-col :span="8">

      <el-form-item label="和本人关系" label-width="100px">
        <el-input v-model="form1.relationship" style="width: 180px"></el-input>
      </el-form-item>

         </el-col>
                <el-col :span="8">


      <el-form-item label="联系人电话" label-width="100px">
        <el-input v-model="form1.contactphone" style="width: 250px"></el-input>
      </el-form-item>

 </el-col>
            </el-row>

      <el-form-item style="text-align: center">
        <el-button type="primary" @click="onSubmit">保存修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import userApi from "@/api/userinfo";
export default {
  data() {
    return {
      form1: {
        name: "",
        age: "",
        sex: "",
        height: "",
        weight: "",
        eyes: "",
        nation: "",
        identitycard: "",
        ismarry: "",
        bornplace: "",
        politicaloutlook: "",
        nowplace: "",
        phone: "",
        healthy: "",
        emergencypeople: "",
        relationship: "",
        contactphone: "",
      },
    }
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    onSubmit() {
      userApi.updateBasicInfo(this.form1).then((response) => {
        this.$message.success(response.message);
        if(response.success){
             setTimeout(function () {
              window.location.href = "/"; //1秒后刷新页面
            }, "1000");
        }
      });
    },
    getUserInfo() {
      userApi.getBasicInfo().then((response) => {
        if(response.data.user){
        this.form1 = response.data.user;
        }
      });
    },
  },
};
</script>