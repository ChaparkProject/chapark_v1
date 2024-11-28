import React, { useState } from 'react';
import API from '../../API/v1';
import { toast } from 'react-toastify';

const Join = () => {
  // 회원가입 데이터 상태
  const [joinData, setjoinData] = useState({
    mberId: '',
    mberPw: '',
    mberPwCaption:'',
    mberPwCheck: '',
    mberPwCheckCaption:'',
    mberEmail: '',
    mberEmailCaption:'',
    mberEmailCode: '',
    mberName: '',
    isIdChecked: false,
    isMberPwCheck: false,
    isEmailVerified: false,
  });  // 이메일 인증 코드 발송 여부 상태
  const [isEmailCodeSent, setIsEmailCodeSent] = useState(false);

  // 회원가입 처리 함수
  const signup = async () => {
    const {
      mberId,
      mberPw,
      mberPwCheck,
      mberEmail,
      mberEmailCode,
      mberName,
      isIdChecked,
      isMberPwCheck,
      isEmailVerified,
    } = joinData;
    // 필수 입력 값 확인
    if (!mberId) {
      toast.warn('아이디를 입력하세요.');
      return;
    }
    if (!mberPw) {
      toast.warn('비밀번호를 입력하세요.');
      return;
    }
    if (!mberPwCheck) {
      toast.warn('비밀번호 확인을 입력하세요.');
      return;
    }
    if (!mberEmail) {
      toast.warn('이메일을 입력하세요.');
      return;
    }
    if (!mberName) {
      toast.warn('이름을 입력하세요.');
      return;
    }
    // 각종 검증 체크
    if (!isIdChecked) {
      toast.warn('아이디 중복 검사를 해주세요.');
      return;
    }
    if (!isEmailVerified) {
      toast.warn('이메일 인증을 완료해주세요.');
      return;
    }
    if (mberPw !== mberPwCheck) {
      toast.warn('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
      return;
    }
    try {
      const response = await API.joinApi.join({
        mberId,
        mberPw,
        mberEmail,
        mberName,
      });
      // 회원가입 성공 처리 로직 추가
      console.log(response);
      toast.success('회원가입에 성공하였습니다. 로그인해주세요.');
      setjoinData({
        mberId: '',
        mberPw: '',
        mberPwCheck: '',
        mberEmail: '',
        mberEmailCode: '',
        mberName: '',
        isIdChecked: false,
        isMberPwCheck,
        isEmailVerified: false,
      });
    } catch (error) {
      console.error(error);
      toast.error('회원가입에 실패하였습니다. 다시 시도해주세요.');
    }
  }

  // 아이디 중복 체크 함수
  const checkIdDuplication = async () => {
    const { mberId } = joinData;
    if (!mberId) {
      toast.warn('아이디를 입력하세요.');
      return;
    }

    try {
      const response = await API.joinApi.idCheck({ mberId });
      console.log("response == > ", response);
      if (response.status == "success") {
        toast.success(response.message);
        setjoinData((prev) => ({ ...prev, isIdChecked: true }));
      } else {
        toast.error(response.message);
        setjoinData((prev) => ({ ...prev, isIdChecked: false }));
      }
    } catch (error) {
      console.error(error);
      toast.error('아이디 중복 체크에 실패하였습니다.');
    }
  };

  // 이메일 인증 코드 발송 함수
  const sendEmailCode = async () => {
    const { EMAIL } = joinData;
    if (!EMAIL) {
      toast.warn('이메일을 입력하세요.');
      return;
    }

    try {
      await API.joinApi.sendEmailCode({ EMAIL });
      toast.success('인증 코드가 이메일로 발송되었습니다.');
      setIsEmailCodeSent(true);
    } catch (error) {
      console.error(error);
      toast.error('인증 코드 발송에 실패하였습니다.');
    }
  };

  // 이메일 인증 코드 확인 함수
  const verifyEmailCode = async () => {
    const { EMAIL, EMAIL_CODE } = joinData;

    if (!EMAIL_CODE) {
      toast.warn('인증 코드를 입력하세요.');
      return;
    }
    try {
      const response = await API.joinApi.verifyEmailCode({ EMAIL, EMAIL_CODE });
      if (response.isVerified) {
        toast.success('이메일 인증에 성공하였습니다.');
        setjoinData((prev) => ({ ...prev, isEmailVerified: true }));
      } else {
        toast.error('인증 코드가 올바르지 않습니다.');
        setjoinData((prev) => ({ ...prev, isEmailVerified: false }));
      }
    } catch (error) {
      console.error(error);
      toast.error('이메일 인증에 실패하였습니다.');
    }
  };
  const validatePassword = (password) => {
    const lengthCriteria = password.length >= 10;
    const hasLetter = /[a-zA-Z]/.test(password);
    const hasNumber = /\d/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
  
    const typeCount = [hasLetter, hasNumber, hasSpecialChar].filter(Boolean).length;

    return (lengthCriteria && typeCount >= 2) || (password.length >= 8 && typeCount >= 3);
  };

  // 입력 변경 핸들러들
  const handlejoinDataChange = (e) => {
    const { name, value } = e.target;
    setjoinData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSignupPwCheckDataChange = (e) => {
    const { name, value } = e.target;
    
    setjoinData(prevState => {
      const newjoinData = { ...prevState, [name]: value };
  
      const isPasswordValid = validatePassword(newjoinData.mberPw);
      const doPasswordsMatch = newjoinData.mberPw === newjoinData.mberPwCheck;
  
      return {
        ...newjoinData,
        mberPwCaption: isPasswordValid ? '' : '최소 8자리 이상이어야 하며, 영문, 숫자, 특수문자 중 두 가지 이상을 포함해야 합니다.',
        mberPwCheckCaption: doPasswordsMatch ? '' : '비밀번호가 일치하지 않습니다.',
        isMberPwCheck: isPasswordValid && doPasswordsMatch,
      };
    });
  };

  // 키다운 이벤트 핸들러
  const handleKeyDown = (e, action) => {
    if (e.key === 'Enter') {
      action();
    }
  };

  return (
    <div className="join-container">
      <header>
        <h1>The Chapark</h1>
        <h2 id='info_header'>회원가입</h2>
        <div>회원가입을 위해 인증 가능한 이메일을<br/>입력해 주세요.</div>
      </header>

      <div className="form-group">
        <label htmlFor="mberId">아이디</label>
        <input
          type="text"
          id="mberId"
          name="mberId"
          value={joinData.mberId}
          onChange={handlejoinDataChange}
          onKeyDown={(e) => handleKeyDown(e, checkIdDuplication)}
          placeholder="아이디를 입력하세요"
        />
        <button type="button" onClick={checkIdDuplication}>
          중복 확인
        </button>
      </div>

      <div className="form-group">
        <label htmlFor="mberPw">비밀번호</label>
        <input
          type="password"
          id="mberPw"
          name="mberPw"
          value={joinData.mberPw}
          onChange={handleSignupPwCheckDataChange}
          placeholder="비밀번호를 입력하세요"
        />
        {joinData.mberPwCaption && (
          <p className="caption-text">{joinData.mberPwCaption}</p>
        )}
      </div>

      <div className="form-group">
        <label htmlFor="mberPwCheck">비밀번호 확인</label>
        <input
          type="password"
          id="mberPwCheck"
          name="mberPwCheck"
          value={joinData.mberPwCheck}
          onChange={handleSignupPwCheckDataChange}
          placeholder="비밀번호 확인을 입력하세요"
        />
        {joinData.mberPwCheckCaption && (
          <p className="caption-text">{joinData.mberPwCheckCaption}</p>
        )}
      </div>

      <div className="form-group">
        <label htmlFor="mberEmail">이메일</label>
        <input
          type="email"
          id="mberEmail"
          name="mberEmail"
          value={joinData.mberEmail}
          onChange={handlejoinDataChange}
          placeholder="이메일을 입력하세요"
        />
        <div>
          <button type="button" onClick={sendEmailCode}>
            인증 코드 발송
          </button>
        </div>
      </div>

      {isEmailCodeSent && (
        <div className="form-group">
          <label htmlFor="mberEmailCode">인증 코드</label>
          <input
            type="text"
            id="mberEmailCode"
            name="mberEmailCode"
            value={joinData.mberEmailCode}
            onChange={handlejoinDataChange}
            placeholder="인증 코드를 입력하세요"
          />
          <button type="button" onClick={verifyEmailCode}>
            인증 코드 확인
          </button>
        </div>
      )}

      <div className="form-group">
        <label htmlFor="mberName">이름</label>
        <input
          type="text"
          id="mberName"
          name="mberName"
          value={joinData.mberName}
          onChange={handlejoinDataChange}
          placeholder="이름을 입력하세요"
        />
      </div>

      <button type="button" onClick={signup} className="signup-button">
        회원가입
      </button>
    </div>
  );
};

export default Join;
