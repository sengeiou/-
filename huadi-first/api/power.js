import request from '@/utils/request'
export default {
  getUserPower() {
    return request({
      url: '/usermain/user-work-info/getUserPower',
      method: 'get'
    })
  }
}