import { Get, Post, objectToQueryString } from '../util';

const config = {headers: {'Content-Type' : 'application/json'}};

export const loginApi = {
  //로그인
  login: async (data) => {
    const response = await Post('/login', data, config)
    localStorage.setItem("token", response.data.token); // 로그인 성공시 JWT 로컬 스토리지에 저장
    return response.data
  },
  //로그아웃
  logout: async (data) => {
    const response = await Post('/logout', data, config)
    return response.data
  },
  //아이디 찾기
  findId: async (data) => {
    const queryString = objectToQueryString(data);
    const response = await Get(`/findId?${queryString}`, config)
    return response.data
  },
  //비밀번호 찾기
  searchPw: async (data) => {
    const queryString = objectToQueryString(data);
    const response = await Get(`/searchPw?${queryString}`, config)
    return response.data
  },
}

export const joinApi = {
  //회원가입
  join: async (data) => {
    const response = await Post('/join', data, config)
    return response.data
  },
  //아이디 중복체크
  idCheck: async (data) => {
    console.log("data ==> ", data);
    const queryString = await objectToQueryString(data);
    console.log("queryString ==> ", queryString);
    const response = await Get(`/idCheck?${queryString}`, config)
    return response.data
  },
  sendEmailCode: async (data) => {
    const response = await Post('/certificationSend', data, config)
    return response.data
  },
  verifyEmailCode: async (data) => {
    const response = await Post('/certificationCheck', data, config)
    return response.data
  }
}