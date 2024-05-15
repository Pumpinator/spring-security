import { request } from '../Controller';
import { useState } from 'react';
import { Input } from './Input';
import { triggerFacebookLogin } from '../Controller';

export function Login() {
  const [facebookLoading, setFacebookLoading] = useState(false);
  const [loading, setLoading] = useState(false);

  function login(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    setLoading(true);
    request({
      url: 'http://localhost:8080/api/login',
      method: 'POST',
      contentType: 'application/json',
      body: JSON.stringify({
        email: data.get('email'),
        password: data.get('password'),
      }),
    })
      .then((response) => {
        window.localStorage.setItem('accessToken', response.accessToken);
        setLoading(false);
        alert('You have successfully signed in');
      })
      .catch((error) => {
        if (error.status === 401) {
          alert('Invalid email or password');
        } else {
          alert('An error occurred');
        }
      });
  }

  function facebookLogin() {
    setFacebookLoading(true);
    FB.login(
      function (response) {
        if (response.status === 'connected') {
          const accessToken = response.authResponse.accessToken;
          return request({
            url: 'http://localhost:8080/api/login/facebook',
            method: 'POST',
            contentType: 'application/json',
            body: JSON.stringify({ accessToken }),
          })
            .then((response) => {
              window.localStorage.setItem('accessToken', response.accessToken);
              setFacebookLoading(false);
            })
            .catch((error) => {
              setFacebookLoading(false);
              if (error.status === 401) {
                alert('Invalid email or password');
              } else {
                alert('An error occurred');
              }
              setFacebookLoading(false);
            });
        } else {
          setFacebookLoading(false);
        }
      },
      { scope: 'email' }
    );
  }

  return (
    <div>
      <form className="mt-8 space-y-6" onSubmit={login}>
        <input type="hidden" name="remember" value="true" />
        <div className="rounded-md shadow-sm -space-y-px">
          <Input
            className="rounded-t-md"
            id="email"
            name="email"
            type="email"
            autoComplete="email"
            required
            placeholder="Email address"
          />
          <Input
            className="rounded-b-md"
            id="password"
            name="password"
            type="password"
            autoComplete="current-password"
            required
            placeholder="Password"
          />
        </div>

        <div>
          <button
            type="submit"
            className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white hover:text-black hover:bg-white transition-all duration-200 ease-out"
            disabled={loading}
          >
            Sign in
          </button>
        </div>
      </form>
      <hr />

      <button
        disabled={facebookLoading}
        className="group mt-4 relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md bg-blue-500/55 text-white transition-all duration-200 ease-out"
        onClick={facebookLogin}
      >
        Sign in with Facebook
      </button>
    </div>
  );
}
