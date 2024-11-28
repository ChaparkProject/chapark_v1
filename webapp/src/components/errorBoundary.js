const ErrorComponent = ({ error, resetErrorBoundary }) => {
  return (
    <div role="alert">
      <p>Error!! </p>
      <pre>{error.message}</pre>
    </div>
  )
}
export default ErrorComponent;