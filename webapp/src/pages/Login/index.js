import React, { useDebugValue, useState } from 'react';
import API from '../../API/v1';
import { handleKeyDown } from '../../utils/common';
import { loginUser } from "../../reducer/userSlice";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

const Login = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("");
  const [loginData, setLoginData] = useState({
    mberId: '',
    mberPw: '',
  });

  // 로그인 처리 함수
  const login = async () => {
    const { mberId, mberPw } = loginData;
    if (!mberId) {
      setErrorMessage("아이디를 입력하세요.")
      return;
    }
    if (!mberPw) {
      setErrorMessage("비밀번호를 입력하세요.")
      return;
    }

    try {
      await API.loginApi.login({ mberId, mberPw }).then(response=>{
        if(response){
          // 로그인 성공 처리 로직 추가
          // 예: 토큰 저장, 페이지 이동 등
          console.log(response);
          navigate("/"); // 로그인 성공시 홈으로 이동합니다.
          dispatch(loginUser(response.userInfo)); // 로그인 성공시 리덕스에 로그인정보 저장

          localStorage.setItem("token", token); // 로그인 성공시 JWT 로컬 스토리지에 저장
        } else {
          setErrorMessage("아이디 또는 비밀번호가 잘못 되었습니다.\n아이디와 비밀번호를 정확히 입력해주세요.")
        }
      });
    } catch (error) {
      setErrorMessage("아이디 또는 비밀번호가 잘못 되었습니다.\n아이디와 비밀번호를 정확히 입력해주세요.")
      console.error(error);
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
        <div className='error-message'>{errorMessage}</div>
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
