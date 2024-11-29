import React, { useState } from 'react';
import API from '../../API/v1';
import { toast } from 'react-toastify';
import { handleKeyDown } from '../../utils/common';

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

  return (
    <section className="login-container">
      <div>
        <h1>The Chapark</h1>
        <h2 id='info-header'>로그인</h2>
      </div>
      <div className="login-form">
        <div className='input-group'>
          <input
            className='form-control'
            type="email"
            placeholder="아이디를 입력하세요"
            name='mberId'
            value={loginData.mberId}
            onChange={handleLoginDataChange}
            onKeyDown={(e) => handleKeyDown(e, login)}
          />
        </div>
        <div className='input-group'>
          <input
            className='form-control'
            type={'password'}
            placeholder="비밀번호를 입력하세요"
            name='mberPw'
            value={loginData.mberPw}
            onChange={handleLoginDataChange}
            onKeyDown={(e) => handleKeyDown(e, login)}
          />
        </div>
        <div className="login-options">
          <a href="/ForgotPassword">비밀번호 찾기</a>
        </div>
        <div className='btn-group'>
          <button onClick={login} className="login-button">로그인</button>
        </div>
        <div className="additional-links">
          <a href="/Join">회원가입</a> | <a href="/ForgotId">아이디 찾기</a>
        </div>
        <div className="division social-login">
          <div id="social-form">
            <p><span>간편로그인</span></p>
            <div className='sicial-btn-group'>
              <button className='sicial-btn'>
                <i className='ico-kakao' aria-hidden="true"></i>
              </button>
              <button className='sicial-btn'>
                <i className='ico-naver' aria-hidden="true"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Login;
