import { Get, Post, objectToQueryString } from '../util';
export const loginApi = {
  //로그인
  login: async (data) => {
    const response = await Post('/login', data, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  },
  //로그아웃
  logout: async (data) => {
    const response = await Post('/logout', data, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  },
  //아이디 찾기
  findId: async (data) => {
    const queryString = objectToQueryString(data);
    const response = await Get(`/findId?${queryString}`, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  },
  //비밀번호 찾기
  searchPw: async (data) => {
    const queryString = objectToQueryString(data);
    const response = await Get(`/searchPw?${queryString}`, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  },
}
export const joinApi = {
  //회원가입
  join: async (data) => {
    const response = await Post('/join', data, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  },
  //아이디 중복체크
  idCheck: async (data) => {
    console.log("data ==> ", data);
    const queryString = await objectToQueryString(data);
    console.log("queryString ==> ", queryString);
    const response = await Get(`/idCheck?${queryString}`, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  },
  sendEmailCode: async (data) => {
    const response = await Post('/certificationSend', data, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  },
  verifyEmailCode: async (data) => {
    const response = await Post('/certificationCheck', data, {headers: {'Content-Type' : 'application/json'}})
    return response.data
  }
}