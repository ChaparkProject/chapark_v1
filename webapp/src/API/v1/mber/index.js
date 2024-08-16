import { Post } from '../util';
export const mberApi = {
  //로그인
  loginApi: async (data) => {
    const response = await Post('/login', data)
    return response
  },
}