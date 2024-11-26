import React from 'react';
import headerLogo from '../assets/images/header_logo_green.png';

const HeaderLogo = (width, height) => {
    return (
        <img 
            src={headerLogo} 
            alt="Header Logo" 
            style={{ 
                width: width, 
                height: height 
            }} 
        />
    );
};

export default HeaderLogo;
