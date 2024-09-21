import React, { useState } from 'react';
import API from '../../API/v1';
import { LoginLogo } from '../../components';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Login = () => {
  const [isLoginMode, setIsLoginMode] = useState(true);
  const [currentStep, setCurrentStep] = useState(1);
  const [loading, setLoading] = useState(false); // 로딩 상태
  const [loginData, setLoginData] = useState({// 로그인 데이터 상태
    mberId: '',
    mberPw: '',
  });
  const [signupData, setSignupData] = useState({// 회원가입 데이터 상태
    mberId: '',                     //아이디
    mberPw: '',                     //비번
    mberPwCaption:'',               //비번 안내사항
    mberPwCheck: '',                //비번 확인
    mberPwCheckCaption:'',          //비번 확인 안내사항
    mberEmail: '',                  //이메일
    mberEmailCaption:'',            //이메일 안내사항
    putNumber: '',                  //이메일 인증코드
    mberName: '',                   //사용자 이름
    isIdChecked: false,             //아이디 중복체크
    isMberPwCheck: false,           //비밀번호 중복체크
    isMberEmailCheck: false,        //이메일 형식체크
    isEmailCodeSent: false,         //이메일 인증코드 발송여부
    isEmailVerified: false,         //이메일 인증코드 확인체크
  });

  // 로그인 처리 함수
  const login = async () => {
    const { mberId, mberPw } = loginData;
    const validations = [
      { condition: !mberId || !mberPw, message: '아이디와 비밀번호를 입력하세요.' },
    ];
    if(validateDataCheck(validations)) return;

    setLoading(true); // 로딩 시작
    try {
      const response = await API.loginApi.login({ mberId, mberPw });
      // 로그인 성공 처리 로직 추가
      console.log(response);
      toast.success('로그인에 성공하였습니다.');
      // 예: 토큰 저장, 페이지 이동 등
    } catch (error) {
      console.error(error);
      toast.error('로그인에 실패하였습니다. 아이디와 비밀번호를 확인하세요.');
    } finally {
      setLoading(false); // 로딩 종료
    }
  };

  // 회원가입 처리 함수
  const signup = async () => {
    const {
      mberId,
      mberPw,
      mberPwCheck,
      mberEmail,
      mberName,
      isIdChecked,
      isMberPwCheck,
      isEmailVerified,
    } = signupData;
    
    const validations = [
      { condition: !mberId, message: '아이디를 입력하세요.' },
      { condition: !mberPw, message: '비밀번호를 입력하세요.' },
      { condition: !mberPwCheck, message: '비밀번호 확인을 입력하세요.' },
      { condition: !mberEmail, message: '이메일을 입력하세요.' },
      { condition: !mberName, message: '이름을 입력하세요.' },
      { condition: !isIdChecked, message: '아이디 중복 검사를 해주세요.' },
      { condition: !isEmailVerified, message: '이메일 인증을 완료해주세요.' },
      { condition: mberPw !== mberPwCheck, message: '비밀번호와 비밀번호 확인이 일치하지 않습니다.' },
    ];
    if(validateDataCheck(validations)) return;
    setLoading(true); // 로딩 시작

    try {
      const response = await API.joinApi.join({
        mberId,
        mberPw,
        mberEmail,
        mberName,
      });
      
      if (response.status == 'success') {
        // 회원가입 성공 처리 로직 추가
        toast.success('회원가입에 성공하였습니다. 로그인해주세요.');
        setIsLoginMode(true);
        setSignupData({
          mberId: '',
          mberPw: '',
          mberPwCaption:'',
          mberPwCheck: '',
          mberPwCheckCaption:'',
          mberEmail: '',
          mberEmailCaption:'',
          putNumber: '',
          mberName: '',
          isIdChecked: false,
          isMberPwCheck: false,
          isMberEmailCheck: false,
          isEmailCodeSent: false,
          isEmailVerified: false
        });
      } else {
        toast.error('인증 코드전송에 실패하였습니다. \n 이메일 형식을 다시 확인해주세요.');
      }
    } catch (error) {
      console.error(error);
      toast.error('회원가입에 실패하였습니다. 다시 시도해주세요.');
    } finally {
      setLoading(false); // 로딩 종료
    }
  };

  // 아이디 중복 체크 함수
  const checkIdDuplication = async () => {
    const { mberId } = signupData;
    const validations = [
      { condition: !mberId , message: '아이디를 입력하세요.' },
    ];
    if(validateDataCheck(validations)) return;

    try {
      const response = await API.joinApi.idCheck({ mberId });
      console.log("response == > ", response);
      if (response.status == "success") {
        toast.success(response.message);
        setSignupData((prev) => ({ ...prev, isIdChecked: true }));
      } else {
        toast.error(response.message);
        setSignupData((prev) => ({ ...prev, isIdChecked: false }));
      }
    } catch (error) {
      console.error(error);
      toast.error('아이디 중복 체크에 실패하였습니다.');
    }
  };
  
  // 이메일 인증 코드 발송 함수
  const sendEmailCode = async () => {
    const { mberEmail } = signupData;
    const validations = [
      { condition: !mberEmail , message: '이메일을 입력하세요.' },
    ];
    if(validateDataCheck(validations)) return;
    setLoading(true); // 로딩 시작

    try {
      const response = await API.joinApi.sendEmailCode({ mberEmail });
      if (response.status == 'success') {
        toast.success('인증 코드가 이메일로 발송되었습니다.');
        setSignupData(prev => ({... prev, isEmailCodeSent: true}));
      } else {
        toast.error('인증 코드전송에 실패하였습니다. \n 이메일 형식을 다시 확인해주세요.');
      }
    } catch (error) {
      console.error(error);
      toast.error('인증 코드 발송에 실패하였습니다.');
    } finally {
      setLoading(false); // 로딩 종료
    }
  };

  // 이메일 인증 코드 확인 함수
  const verifyEmailCode = async () => {
    const { mberEmail, putNumber } = signupData;
    const validations = [
      { condition: !putNumber , message: '인증 코드를 입력하세요.' },
    ];
    if(validateDataCheck(validations)) return;
    setLoading(true); // 로딩 시작

    try {
      const response = await API.joinApi.verifyEmailCode({ mberEmail, putNumber });
      if (response.status == 'success') {
        toast.success('이메일 인증에 성공하였습니다.');
        setSignupData((prev) => ({ ...prev, isEmailVerified: true }));
      } else {
        toast.error('인증 코드가 올바르지 않습니다.');
        setSignupData((prev) => ({ ...prev, isEmailVerified: false }));
      }
    } catch (error) {
      console.error(error);
      toast.error('이메일 인증에 실패하였습니다.');
    } finally {
      setLoading(false); // 로딩 종료
    }
  };

  // 비밀번호 형식 체크 함수
  const validatePassword = (password) => {
    const lengthCriteria = password.length >= 10;
    const hasLetter = /[a-zA-Z]/.test(password);
    const hasNumber = /\d/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
  
    const typeCount = [hasLetter, hasNumber, hasSpecialChar].filter(Boolean).length;
  
    return (lengthCriteria && typeCount >= 2) || (password.length >= 8 && typeCount >= 3);
  };

  // 이메일 형식 체크 함수 ("하나 이상의 문자" + "@" + "하나 이상의 문자" + . + "최소 2글자 이상")
  const validateEmail = (email) => {
    return /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(email) && email.length > 0;
  };

  // 필수 입력 값 확인 함수
  const validateDataCheck = (validations) => {
    for (const { condition, message } of validations) {
      if (condition) {
        toast.warn(message);
        return false;
      }
    }
    return true;
  };

  // 비밀번호 입력 변경 핸들러
  const handleSignupPwCheckDataChange = (e) => {
    const { name, value } = e.target;
    
    setSignupData(prevState => {
      const newSignupData = { ...prevState, [name]: value };
  
      const isPasswordValid = validatePassword(newSignupData.mberPw);
      const doPasswordsMatch = newSignupData.mberPw === newSignupData.mberPwCheck;
  
      return {
        ...newSignupData,
        mberPwCaption: isPasswordValid ? '' : '최소 8자리 이상이어야 하며, 영문, 숫자, 특수문자 중 두 가지 이상을 포함해야 합니다.',
        mberPwCheckCaption: doPasswordsMatch ? '' : '비밀번호가 일치하지 않습니다.',
        isMberPwCheck: isPasswordValid && doPasswordsMatch,
      };
    });
  };

  // 이메일 입력 변경 핸들러
  const handleEmailDataChange = (e) => {
    const { name, value } = e.target;
    
    setSignupData(prevState => {
      const newSignupData = { ...prevState, [name]: value };
      const isEmailValid = validateEmail(newSignupData.mberEmail);
  
      return {
        ...newSignupData,
        mberEmailCaption: isEmailValid ? '유효한 이메일입니다.' : '유효하지 않은 이메일 형식입니다.',
        isMberEmailCheck: isEmailValid,
      };
    });
  };

  // 로그인 입력 변경 핸들러
  const handleLoginDataChange = (e) => {
    const { name, value } = e.target;
    setLoginData((prev) => ({ ...prev, [name]: value }));
  };

  // 회원가입 입력 변경 핸들러
  const handleSignupDataChange = (e) => {
    const { name, value } = e.target;
    setSignupData((prev) => ({ ...prev, [name]: value }));
  };

  // 모드 전환 핸들러
  const handleModeSwitch = () => {
    setIsLoginMode((prev) => !prev);
  };

  // 키다운 이벤트 핸들러
  const handleKeyDown = (e, action) => {
    if (e.key === 'Enter') {
      action();
    }
  };

return (
  <div className="panel shadow1">
    {isLoginMode ? ( //로그인
      <>
        <div className="login-form blind">
          <LoginLogo width={180} height={'auto'} />
        </div>
        <div className="login-form">
          <div className="panel-switch">
            <button
              id="sign_up"
              className={!isLoginMode ? 'opacity-half' : ''}
              onClick={handleModeSwitch}
              disabled={!isLoginMode}
            >
              회원가입
            </button>
            <button
              id="log_in"
              className={isLoginMode ? 'opacity-half' : ''}
              onClick={handleModeSwitch}
              disabled={isLoginMode}
            >
              로그인
            </button>
          </div>
          <h1 id="title-login">로그인</h1>
          <fieldset id="login-fieldset">
            <input
              className="login"
              name="mberId"
              type="text"
              placeholder="아이디"
              onChange={handleLoginDataChange}
              onKeyDown={(e) => handleKeyDown(e, login)}
              value={loginData.mberId}
            />
            <input
              className="login"
              name="mberPw"
              type="password"
              placeholder="비밀번호"
              onChange={handleLoginDataChange}
              onKeyDown={(e) => handleKeyDown(e, login)}
              value={loginData.mberPw}
            />
          </fieldset>
          <button
            className="login_form button"
            onClick={login}
            disabled={loading}
          >
            {loading ? '로그인 중...' : '로그인'}
          </button>
          <p>
            <a
              id="lost-password-link"
              href="#"
            >
              아이디나 비밀번호를 잊으셨나요?
            </a>
          </p>
        </div>
      </>
    ) : ( //회원가입
      <>
        <div className="login-form">
          <div className="panel-switch">
            <button
              id="sign_up"
              className={!isLoginMode ? 'opacity-half' : ''}
              onClick={handleModeSwitch}
              disabled={!isLoginMode}
            >
              회원가입
            </button>
            <button
              id="log_in"
              className={isLoginMode ? 'opacity-half' : ''}
              onClick={handleModeSwitch}
              disabled={isLoginMode}
            >
              로그인
            </button>
          </div>
          {currentStep === 1 && (
            <>
              <h1 id="title-signup">회원가입 - 단계 1</h1>
              <fieldset id="signup-fieldset">
                <div className="input-group">
                  <input
                    className="signup"
                    name="mberName"
                    type="text"
                    placeholder="이름"
                    onChange={handleSignupDataChange}
                    value={signupData.mberName}
                  />
                </div>
                <div className="input-group">
                  <input
                    className="signup"
                    name="mberId"
                    type="text"
                    placeholder="아이디"
                    onChange={handleSignupDataChange}
                    value={signupData.mberId}
                    onKeyDown={(e) => handleKeyDown(e, checkIdDuplication)}
                  />
                  <button
                    className='check-button'
                    onClick={checkIdDuplication}
                  >
                  {loading ? '중복확인 중...' : '중복확인'}
                  </button>
                </div>
              </fieldset>
              <button
                className="signup_form button"
                onClick={() => {
                  if (signupData.isIdChecked) {
                    setCurrentStep(2);
                  } else {
                    toast.warn('아이디 중복 검사를 해주세요.');
                  }
                }}
              >
                다음
              </button>
            </>
          )}
          {currentStep === 2 && (
            <>
              <h1 id="title-signup">회원가입 - 단계 2</h1>
              <fieldset id="signup-fieldset">
                <input
                  className="signup"
                  name="mberPw"
                  type="password"
                  placeholder="비밀번호"
                  onChange={handleSignupPwCheckDataChange}
                  value={signupData.mberPw}
                />
                <span id='caption'>{signupData.mberPwCaption}</span>
                <input
                  className="signup"
                  name="mberPwCheck"
                  type="password"
                  placeholder="비밀번호 확인"
                  onChange={e => {
                    handleSignupDataChange(e);
                    handleSignupPwCheckDataChange(e);
                  }}
                  value={signupData.mberPwCheck}
                />
                <span id='caption'>{signupData.mberPwCheckCaption}</span>
              </fieldset>
              <button
                className="signup_form button"
                onClick={() => {
                  if (signupData.isMberPwCheck) {
                    setCurrentStep(3);
                  } else {
                    toast.warn('비밀번호를 확인해주세요.');
                  }
                }}
              >
                다음
              </button>
            </>
          )}
          {currentStep === 3 && (
            <>
              <h1 id="title-signup">회원가입 - 단계 3</h1>
              <fieldset id="signup-fieldset">
                <div className="input-group">
                  <input
                    className="signup"
                    name="mberEmail"
                    type="email"
                    placeholder="이메일"
                    onChange={handleEmailDataChange}
                    value={signupData.mberEmail}
                    onKeyDown={(e) => handleKeyDown(e, sendEmailCode)}
                  />
                  <button
                    className="check-button"
                    onClick={sendEmailCode}
                    disabled={!signupData.isMberEmailCheck}
                  >
                  {loading ? '인증코드 발송 중...' : '인증코드 발송'}
                  </button>
                </div>
                <span id='caption'>{signupData.mberEmailCaption}</span>
                {signupData.isEmailCodeSent && (
                  <div className="input-group">
                    <input
                      className="signup"
                      name="putNumber"
                      type="text"
                      placeholder="인증 코드 입력"
                      onChange={handleSignupDataChange}
                      value={signupData.putNumber}
                      onKeyDown={(e) => handleKeyDown(e, verifyEmailCode)}
                    />
                    <button
                      className='check-button'
                      onClick={verifyEmailCode}
                      disabled={!signupData.putNumber}
                    >
                    {loading ? '인증 확인 중...' : '인증 확인'}
                    </button>
                  </div>
                )}
              </fieldset>
              <button
                className="signup_form button"
                disabled={loading}
                onClick={() => {
                  if (signupData.isEmailVerified) {
                    signup();
                  } else {
                    toast.warn('이메일 인증을 완료해주세요.');
                  }
                }}
              >
                회원가입
              </button>
            </>
          )}
        </div>
        <div className="login-form blind">
          <LoginLogo width={180} height={'auto'} />
        </div>
      </>
    )}
  </div>
);

};

export default Login;
