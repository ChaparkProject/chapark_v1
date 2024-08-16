import React from 'react';

const Main = ({ headerHeight, children }) => {
    return (
        <main id="main" style={{ marginTop: `${headerHeight}px` }}>
            {children}
        </main>
    )
}
export default Main;