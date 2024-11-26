import React, { useState } from 'react';
import axios from 'axios';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);

  const handleLogin = async () => {
    try {
      const response = await axios.post('/api/login', {
        email,
        password,
        rememberMe,
      });
      console.log('Login successful:', response.data);
    } catch (error) {
      console.error('Login failed:', error);
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
            placeholder="이메일을 입력하세요"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className='input_group'>
          <input
            className='form_control'
            type={'password'}
            placeholder="비밀번호를 입력하세요"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>




        <div className="login-options mt12">
          <a href="/forgot-password">비밀번호 찾기</a>
        </div>








        <div className='btn_group mt28 position_re'>
          <button onClick={handleLogin} className="login-button">이메일로 로그인</button>
        </div>








        <div className="additional-links">
          <a href="/register">회원가입</a> | <a href="/find-email">이메일 찾기</a>
        </div>






        
        <div className="division social-login">
          <form id="socialFrm" name="socialFrm" method="post">
            <p><span>간편로그인</span></p>
            
          </form>
        </div>

        
      </div>
    </section>
  );
};

export default Login;
