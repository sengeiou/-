import request from '@/utils/request'
export default {
  //下面是根据部门查找员工list，发工资调用该接口
  findUser(departmentid){
    return request({
      url: `/usermain/user-work-info/getUserListByDepartment/${departmentid}`,
      method: 'get'
    })
  },
  //下面时查看工作信息的接口
  getGreatDealInfoPlus(page, size, userQuery) {
    return request({
      url: `/usermain/user-work-info/getGreatDealInfoPlus/${page}/${size}`,
      method: 'post',
      data: userQuery
    })
  },

  getNormalDealInfoPlus(current, size, userQuery) {
    return request({
     url: '/usermain/user-work-info/getNormalDealInfoPlus/'+current+'/'+size,
      method: 'post',
      data: userQuery
    })
  },
  //下面是待审批工作信息查看和审批接口
  getNormalDealInfo(current, size, userQuery) {
    return request({
     url: '/usermain/user-work-info/getNormalDealInfo/'+current+'/'+size,
      method: 'post',
      data: userQuery
    })
  },
  getGreatDealInfo(page, size, userQuery) {
    return request({
      url: `/usermain/user-work-info/getGreatDealInfo/${page}/${size}`,
      method: 'post',
      data: userQuery
    })
  },
  dealNormalApply(userid, result) {
    return request({
      url: `/usermain/user-work-info/dealNormalApply/${userid}/${result}`,
      method: 'get'
    })
  }, dealGreatApply(userid, result) {
    return request({
      url: `/usermain/user-work-info/dealGreatApply/${userid}/${result}`,
      method: 'get'
    })
  },


  //下面是获取自身基本信息和工作信息接口
  updateBasicInfo(member) {
    return request({
      url: '/usermain/user-basic-info/updateUserBasicInfo',
      method: 'post',
      data: member
    })
  },
  getBasicInfo() {
    return request({
      url: '/usermain/user-basic-info/getUserBasicInfo',
      method: 'get'
    })
  },
  updateWorkInfo(member) {
    return request({
      url: '/usermain/user-work-info/updateWorkInfo',
      method: 'post',
      data: member//用户提交自己工作信息的申请
    })
  },
  updateNormalWorkInfoByUserId(member,id) {
    return request({
      url: `/usermain/user-work-info/updateNormalWorkInfoByUserId/${id}`,
      method: 'post',
      data: member//该方法用于直接修改员工的工作信息
    })
  },
  updateGreatWorkInfoByUserId(member,id) {
    return request({
      url: `/usermain/user-work-info/updateGreatWorkInfoByUserId/${id}`,
      method: 'post',
      data: member//该方法用于直接修改员工的工作信息
    })
  },
  getWorkInfo() {
    return request({
      url: '/usermain/user-work-info/getWorkInfo',
      method: 'get'//用户获取自己的工作信息
    })
  },
  getNormalWorkInfoByUserId(id) {
    return request({
      url: `/usermain/user-work-info/getNormalWorkInfoByUserId/${id}`,
      method: 'get'//该方法用于获取需要直接修改工作信息的员工
    })
  },
  getGreatWorkInfoByUserId(id) {
    return request({
      url: `/usermain/user-work-info/getGreatWorkInfoByUserId/${id}`,
      method: 'get'//该方法用于获取需要直接修改工作信息的员工
    })
  },
  getDepartment() {
    return request({
      url: '/usermain/department/getAllDepartment',
      method: 'get'
    })
  }
}