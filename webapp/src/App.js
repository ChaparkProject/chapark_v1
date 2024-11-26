import React, { useState, useRef, useEffect } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import { Header, Main, Footer } from './layouts';
import Login from './pages/Login';

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

  // 특정 경로에 대해 레이아웃을 변경
  const isLoginPage = location.pathname.startsWith('/Login');
  const isJoinPage = location.pathname.startsWith('/Join');

  return (
    <>
      {!isLoginPage && !isJoinPage && <Header ref={headerRef} />}
      <Main headerHeight={headerHeight == 0 ? 80 : headerHeight}>
        <Routes>
          <Route path="/Login" element={<Login />} />
          <Route path="/Join" element={<Login />} />
        </Routes>
      </Main>
      
      <Footer />
      <ToastContainer 
        position="top-center"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="dark"
      />
    </>
  );
}

export default App;
