import React, { useState, useRef, useEffect } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import { ErrorBoundary } from 'react-error-boundary'

import { Header, Main, Footer } from './layouts';
import ErrorComponent from './components/errorBoundary';
import Login from './pages/Login';
import Join from './pages/Join';
import {ForgotPassword, ForgotId} from './pages/Forgot';

const App = () => {
  const [headerHeight, setHeaderHeight] = useState(0);
  const headerRef = useRef(null);
  const location = useLocation(); // 현재 URL 확인

  useEffect(() => {
      if (headerRef.current) {
          setHeaderHeight(headerRef.current.offsetHeight);
      }

      const handleResize = () => {
          if (headerRef.current) {
              setHeaderHeight(headerRef.current.offsetHeight);
          }
      };
      window.addEventListener('resize', handleResize);
      return () => window.removeEventListener('resize', handleResize);
  }, []);

  const logError = (error, errorInfo) => {
  	console.log({ error, errorInfo })
  }

  // 특정 경로에 대해 레이아웃을 변경
  const isLoginPage = location.pathname.startsWith('/Login');
  const isJoinPage = location.pathname.startsWith('/Join');
  const isForgotPasswordPage = location.pathname.startsWith('/ForgotPassword');
  const isForgotIdPage = location.pathname.startsWith('/ForgotId');

  const isHeader = !isLoginPage && !isJoinPage && !isForgotPasswordPage && !isForgotIdPage;


  return (
    <ErrorBoundary 
       FallbackComponent={ErrorComponent}
       onError={logError}
    >
      {isHeader && <Header ref={headerRef} />}
      <Main headerHeight={headerHeight == 0 ? 80 : headerHeight}>
        <Routes>
          <Route path="/Login" element={<Login />} />
          <Route path="/Join" element={<Join />} />
          <Route path="/ForgotPassword" element={<ForgotPassword />} />
          <Route path="/ForgotId" element={<ForgotId />} />
        </Routes>
      </Main>
      
      <Footer />
    </ErrorBoundary>
  );
}

export default App;
