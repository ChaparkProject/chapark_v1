import React from 'react';
import loginLogo from '../assets/images/login_logo_green.png';

const LoginLogo = (width, height) => {
    return (
        <img 
            src={loginLogo} 
            alt="Login Logo" 
            style={{ 
                width: width, 
                height: height 
            }} 
        />
    );
};

export default LoginLogo;
