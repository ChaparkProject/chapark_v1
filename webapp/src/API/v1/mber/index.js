import { Post } from '../util';
const mberApi = {
  //로그인
  loginApi: async (data) => {
    const response = await Post('/login', data, {headers: {'Content-Type' : 'application/json'}})
    return response
  },
}

export default mberApi;