import request from '@/utils/request'
export default {
    lockByEmail(forminfo) {
    return request({
      url: '/userservice/user/lockByEmail',
      method: 'post',
      data:forminfo
    })
  },
  lockByPhone(forminfo) {
    return request({
      url: '/userservice/user/lockByPhone',
      method: 'post',
      data:forminfo
    })
  },
  isLock(){
    return request({
        url: '/userservice/user/isLock',
        method: 'get'
      })
  }
}