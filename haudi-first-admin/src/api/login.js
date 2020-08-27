import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/userservice/user/adminLogin',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/userservice/user/adminGetInfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
