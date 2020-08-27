import request from '@/utils/request'
export default {
  //下面方法用于登录之后修改密码，再下面2个方面用于找回密码
  changepass(form){
    return request({
      url: `/userservice/user/changepass`,
      method: 'post',
      data: form
    })
  },
  changepassByPhone(member){
    return request({
      url: '/userservice/user/changepassByPhone',
      method: 'post',
      data: member
    })
  },
  changepassByEmail(member){
    return request({
      url: '/userservice/user/changepassByEmail',
      method: 'post',
      data: member
    })
  },
  sendPhone(mobile) {
    return request({
      url: `/sendinfo/sendPhone/${mobile}`,
      method: 'get'
    })
  },
  sendEmail(email) {
    return request({
      url: `/sendinfo/sendEmail/${email}`,
      method: 'get'
    })
  },

  registByPhone(member) {
    return request({
      url: '/userservice/user/registByPhone',
      method: 'post',
      data: member
    })
  },
  registByEmail(member) {
    return request({
      url: '/userservice/user/registByEmail',
      method: 'post',
      data: member
    })
  }
}