import request from '@/utils/request'
export default {
    getdepartmentPeople(){
        return request({
            url: '/usermain/department/getdepartmentPeople',
            method: 'get'
          })
    },
    adddepartment(department){
    return request({
      url: `/usermain/department/adddepartment`,
      method: 'post',
      data:department
    })
  },
  getAllPower() {
    return request({
      url: '/usermain/power/getAllPower',
      method: 'get'
    })
  },
  getDepartment() {
    return request({
      url: '/usermain/department/getAllDepartment',
      method: 'get'
    })
  },
  //下面这个方法会返回每个部门的权限list，修改部门权限调用
  getDepartment2() {
    return request({
      url: '/usermain/department/getAllDepartment2',
      method: 'get'
    })
  },
  //下面方法批量修改部门权限
  changePower(department){
    return request({
      url: `/usermain/department/changePower`,
      method: 'post',
      data:department
    })
  },
  findUser(departmentid){
    return request({
      url: `/usermain/user-work-info/getUserListByDepartment/${departmentid}`,
      method: 'get'
    })
  },

}