import React, { useState } from 'react';
import API from '../../API/v1';
import { toast } from 'react-toastify';

const Login = () => {
  const [loginData, setLoginData] = useState({
    mberId: '',
    mberPw: '',
  });

  // 로그인 처리 함수
  const login = async () => {
    const { mberId, mberPw } = loginData;
    if (!mberId) {
      toast.warn('아이디를 입력하세요.');
      return;
    }
    if (!mberPw) {
      toast.warn('비밀번호를 입력하세요.');
      return;
    }
    try {
      const response = await API.loginApi.login({ mberId, mberPw });
      // 로그인 성공 처리 로직 추가
      console.log(response);
      toast.success('로그인에 성공하였습니다.');
      // 예: 토큰 저장, 페이지 이동 등
    } catch (error) {
      console.error(error);
      toast.error('로그인에 실패하였습니다. 아이디와 비밀번호를 확인하세요.');
    }
  };

  const handleLoginDataChange = (e) => {
    const { name, value } = e.target;
    setLoginData((prev) => ({ ...prev, [name]: value }));
  };

  // 키다운 이벤트 핸들러
  const handleKeyDown = (e, action) => {
    if (e.key === 'Enter') {
      action();
    }
  };

  return (
    <section className="login-container">
      <div>
        <h1>The Chapark</h1>
        <h2 id='info_header'>로그인</h2>
      </div>
      <div className="login-form">
        <div className='input_group'>
          <input
            className='form_control'
            type="email"
            placeholder="아이디를 입력하세요"
            value={loginData.mberId}
            onChange={handleLoginDataChange}
            onKeyDown={(e) => handleKeyDown(e, login)}
          />
        </div>
        <div className='input_group'>
          <input
            className='form_control'
            type={'password'}
            placeholder="비밀번호를 입력하세요"
            value={loginData.mberPw}
            onChange={handleLoginDataChange}
            onKeyDown={(e) => handleKeyDown(e, login)}
          />
        </div>
        <div className="login-options mt12">
          <a href="/forgot-password">비밀번호 찾기</a>
        </div>
        <div className='btn_group mt28 position_re'>
          <button onClick={login} className="login-button">로그인</button>
        </div>
        <div className="additional-links">
          <a href="/Join">회원가입</a> | <a href="/find-email">이메일 찾기</a>
        </div>
        <div className="division social-login">
          <form id="socialFrm" name="socialFrm" method="post">
            <p><span>간편로그인</span></p>
            <div className='sicial-btn-group'>
              <button className='sicial-btn'>
                <i className='ico_kakao' aria-hidden="true"></i>
              </button>
              <button className='sicial-btn'>
                <i className='ico_naver' aria-hidden="true"></i>
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

export default Login;
