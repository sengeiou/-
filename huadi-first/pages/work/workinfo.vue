<template>
  <div>
    <el-form ref="form1" :model="form1" label-width="80px">
      审核状态：
      <i class="el-icon-check" v-if="form1.statis=='pass'">通过</i>
      <i class="el-icon-close" v-if="form1.statis=='defeat'">失败</i>
      <i class="el-icon-s-promotion" v-if="form1.statis=='wait'">等待</i>
           <el-divider></el-divider>
 <el-row>
                <el-col :span="6">

      <el-form-item label="姓名">
        <el-input v-model="form1.name" :disabled="true" style="width: 100px"></el-input>
      </el-form-item>

 </el-col>
                <el-col :span="6">

      <el-form-item label="性别">
        <el-select v-model="form1.sex" placeholder="请选择性别" :disabled="true" style="width: 150px">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>

          </el-col>
                <el-col :span="6">

      <el-form-item label="年龄">
        <el-input-number
           style="width: 120px"
        :disabled="true"
          :min="0"
          v-model="form1.age"
          controls-position="right"
          placeholder="年龄必须为整数"
        />
      </el-form-item>

        
       </el-col>
                <el-col :span="6">


<el-form-item label="工作部门">
    <el-select v-model="form1.departmentid" placeholder="请选择部门" :disabled="true">
      <el-option :label="de.name" :value="de.id" v-for="de in list" :key="de"></el-option>
    </el-select>
  </el-form-item>

 </el-col>
            </el-row>
      
      <el-form-item label="教育情况">
        <el-input :disabled="true" type="textarea" v-model="form1.education"></el-input>
      </el-form-item>
      <el-form-item label="工作情况">
        <el-input :disabled="true" type="textarea" v-model="form1.workinfo"></el-input>
      </el-form-item>

      <el-form-item label="获奖信息">
        <el-input :disabled="true" type="textarea" v-model="form1.awardsinfo"></el-input>
      </el-form-item>

      <el-form-item label="惩罚记录">
        <el-input :disabled="true" type="textarea" v-model="form1.punishmentinfo"></el-input>
      </el-form-item>

      
       <el-row>
                <el-col :span="6">

      <el-form-item label="手机号">
        <el-input :disabled="true" v-model="form1.phone" style="width: 250px"></el-input>
      </el-form-item>

           </el-col>
                <el-col :span="6">

   <el-form-item label="居住地">
        <el-input :disabled="true" v-model="form1.address" style="width: 250px"></el-input>
      </el-form-item>

     </el-col>
                <el-col :span="6">

         <el-form-item label="工资">
        <el-input :disabled="true" v-model="form1.salary" style="width: 250px"></el-input>
      </el-form-item>

         </el-col>
            </el-row>

    </el-form>
  </div>
</template>
<script>
import userApi from "@/api/userinfo";
export default {
  data() {
    return {
      list:[],
      form1: {
        name: '',
        age: "",
        sex: "",
        education: "",
        departmentid: "",
        workinfo: "",
        awardsinfo: "",
        punishmentinfo: "",
        phone: "",
        address: "",
        salary: "",
        statis:""
      },
    }
  },
  created() {
    this.getUserInfo();
    this.getDepartment();
  },
  methods: {
    getUserInfo() {
      userApi.getWorkInfo().then((response) => {
        if(response.data.user){
        this.form1 = response.data.user;
        }
      });
    },
    getDepartment(){
      userApi.getDepartment().then((response) => {
        this.list = response.data.list;
      });
    }
  },
};
</script>