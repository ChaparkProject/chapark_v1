import React, { useState, useRef, useEffect } from 'react';
import { Route, Routes } from 'react-router-dom';
import { Header, Main, Footer } from './layouts';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import Login from './pages/Login';

const App = () => {
  const [headerHeight, setHeaderHeight] = useState(0);
  const headerRef = useRef(null);

  useEffect(() => {
      if (headerRef.current) {
          setHeaderHeight(headerRef.current.offsetHeight);
      }

      // Handle window resize to adjust header height
      const handleResize = () => {
          if (headerRef.current) {
              setHeaderHeight(headerRef.current.offsetHeight);
          }
      };

      window.addEventListener('resize', handleResize);
      return () => window.removeEventListener('resize', handleResize);
  }, []);

  return (
    <>
      <Header ref={headerRef}/>
      <Main headerHeight={headerHeight}>
        <Routes>
          <Route path="/Login" element={<Login />} />
        </Routes>
      </Main>
      <Footer/>
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
