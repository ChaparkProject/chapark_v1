import React from 'react';

const Main = ({ headerHeight, children }) => {
    return (
        <main id="main" style={{ padding: `${headerHeight}px 0px` }}>
            {children}
        </main>
    )
}
export default Main;