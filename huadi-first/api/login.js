import request from '@/utils/request'
export default {
    loginByPhone(member) {
    return request({
      url: '/userservice/user/phoneLogin',
      method: 'post',
      data: member
    })
  },
  loginByEmail(member) {
    return request({
      url: '/userservice/user/emailLogin',
      method: 'post',
      data: member
    })
  },
  getLoginInfo() {
    return request({
      url: '/userservice/user/get-login-info',
      method: 'get'
      // 通过请求头发送token
    //   headers: { 'token': cookie.get('jwt_token') }
    })
  },

  //修改头像专用api
  saveAvator(userInfo){
    return request({
      url: '/userservice/user/changeAvator',
      method: 'post',
      data: userInfo
    })
  }
}