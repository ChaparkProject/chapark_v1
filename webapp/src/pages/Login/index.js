
import React, { useState } from 'react';
import mberApi from '../../API/v1';

const Login = () =>{
  const [isLoginMode, setIsLoginMode] = useState(true);
  const [loginData, setLoginData] = useState({id:"", pw:""});
  const [signupData, setSignupData] = useState({id:"", pw:"", pwCheck:"", email:""});

  const login = () => {
    mberApi.loginApi(loginData)
      .then((response) => {
        console.log(response)
      })
      .catch((error) => {
        console.error(error)
      })
  }

  const signup = () => {

  }


  const handleSignupData = (value, code) => {
      setSignupData(prev => ({...prev, [code]: value }));
  };
  
  const handleLoginData = (value, code) => {
      setLoginData(prev => ({...prev, [code]: value }));
  };

  const handleSignUpClick = () => {
      setIsLoginMode(false);
  };

  const handleLoginClick = () => {
      setIsLoginMode(true);
  };

  return (
    <div class="panel shadow1">

      {
        isLoginMode ? //로그인
        <>
          <div class="login-form blind" />
          <div class="login-form">
            <div class="panel-switch animated fadeIn">
              <button 
                id="log_in" 
                className={isLoginMode ? "active-button" : ""}
                onClick={handleLoginClick}
              >
                로그인
              </button>
              <button 
                id="sign_up" 
                className={!isLoginMode ? "active-button" : ""}
                onClick={handleSignUpClick}
              >
                회원가입
              </button>
            </div>
            <h1 class="animated fadeInUp animate1" id="title-signup">Chaprk 커뮤니티</h1>
            <>
              <fieldset id="login-fieldset">
                <input 
                  class="login animated fadeInUp animate2" 
                  name="username" 
                  type="textbox"
                  placeholder="Username" 
                  onChange={e => handleLoginData(e.target.value, "id")}
                  value={loginData.id}
                />
                <input 
                  class="login animated fadeInUp animate3" 
                  name="password" 
                  type="password" 
                  placeholder="Password" 
                  onChange={e => handleLoginData(e.target.value, "pw")}
                  value={loginData.pw}
                />
              </fieldset>
              <input 
                class="login_form button animated fadeInUp animate4" 
                type="submit" 
                id="login-form-submit" 
                value="Log in" 
                onClick={e=>login()}
              />
            </>
    
            <p><a id="lost-password-link" href="" class="animated fadeIn animate5">I forgot my  login or password (!)</a></p>
          </div>
        </>

        : //회원가입
        <>
          <div class="signup-form">
            <div class="panel-switch animated fadeIn">
              <button 
                id="log_in" 
                className={isLoginMode ? "active-button" : ""}
                onClick={handleLoginClick}
              >
                로그인
              </button>
              <button 
                id="sign_up" 
                className={!isLoginMode ? "active-button" : ""}
                onClick={handleSignUpClick}
              >
                회원가입
              </button>
            </div>
            <>
              <fieldset id="login-fieldset">
                <input 
                  class="login animated fadeInUp animate2" 
                  name="username" 
                  type="textbox"
                  placeholder="Username" 
                  onChange={e => handleSignupData(e.target.value, "id")}
                  value={loginData.id}
                />
                <input 
                  class="login animated fadeInUp animate3" 
                  name="password" 
                  type="password" 
                  placeholder="Password" 
                  onChange={e => handleSignupData(e.target.value, "pw")}
                  value={loginData.pw}
                />
                <input 
                  class="login animated fadeInUp animate3" 
                  name="passwordCheck" 
                  type="passwordCheck" 
                  placeholder="Password Check" 
                  onChange={e => handleSignupData(e.target.value, "pwCheck")}
                  value={loginData.pw}
                />
                <input 
                  class="login animated fadeInUp animate3" 
                  name="email" 
                  type="email" 
                  placeholder="Email" 
                  onChange={e => handleSignupData(e.target.value, "Email")}
                  value={loginData.pw}
                />
              </fieldset>
              <input 
                class="login_form button animated fadeInUp animate4" 
                type="submit" 
                id="login-form-submit" 
                value="Sign Up" 
              />
            </>
          </div>
          <div class="login-form blind" />
        </>
      }
  
    </div>
  )
}
export default Login;