import React, { useState } from 'react';
import API from '../../API/v1';
import { toast } from 'react-toastify';
import { handleKeyDown } from '../../utils/common';

const ForgotPassword = () => {
  const [forgotPwData, setForgotPwData] = useState({
    mberId: '',
    mberPw: '',
  });

  const findPassword = (e) => {

  }

  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setForgotPwData((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <section className="forgot-password-container">
      <div>
        <h1>The Chapark</h1>
        <h2 id='info-header'>비밀번호 찾기</h2>
        <div>
          이메일 로그인 회원에게만 제공되는 기능입니다.<br/>
          로그인 이메일을 입력하시면<br/>
          비밀번호 재설정 링크를 메일로 보내드립니다.
        </div>
      </div>
      <div className="login-form">
        <div className='input-group'>
          <input
            className='form-control'
            type="email"
            placeholder="아이디를 입력하세요"
            name='mberId'
            value={forgotPwData.mberId}
            onChange={handleOnChange}
            onKeyDown={(e) => handleKeyDown(e, findPassword)}
          />
        </div>
        <div className='btn-group'>
          <button onClick={findPassword} className="login-button">인증메일찾기</button>
        </div>
      </div>
    </section>
  );
};

const ForgotId = () => {
  const [forgotIdData, setForgotIdData] = useState({
    mberId: '',
    mberPw: '',
  });

  const findId = (e) => {

  }

  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setForgotIdData((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <section className="forgot-id-container">
      <div>
        <h1>The Chapark</h1>
        <h2 id='info-header'>아이디 찾기</h2>
        <div>
          이메일 로그인 회원에게만 제공되는 기능입니다.<br/>
          로그인 이메일을 입력하시면<br/>
          비밀번호 재설정 링크를 메일로 보내드립니다.
        </div>
      </div>
      <div className="login-form">
        <div className='input-group'>
          <input
            className='form-control'
            type="email"
            placeholder="이메일을 입력하세요"
            name='mberId'
            value={forgotIdData.mberId}
            onChange={handleOnChange}
            onKeyDown={(e) => handleKeyDown(e, findId)}
          />
        </div>
        <div className='btn-group'>
          <button onClick={findId} className="login-button">인증메일찾기</button>
        </div>
      </div>
    </section>
  );
};

export {ForgotPassword, ForgotId};

