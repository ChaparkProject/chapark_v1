
const handleKeyDown = (e, action) => {
  if (e.key === 'Enter') {
    action();
  }
};

export {handleKeyDown};