import { Login } from './Login';
import { useState } from 'react';
import { request } from '../Controller';
import springLogo from '../assets/spring.svg';

export function Main() {
  return (
    <main className="flex flex-col items-center min-h-screen gap-8 pt-12">
      <img src={springLogo} alt="Spring Logo" className="w-32 h-32" />
      <h1 className="text-4xl font-bold text-center">
        Welcome to the <code>spring-security</code> application!
      </h1>
      <p className="text-lg text-center">
        This is a <code>spring-security</code> application that demonstrates how
        to use Spring Boot as a backend and React as a frontend. The backend use
        Spring Security to secure the application with JWT and OAuth2. This let
        the users to sign in with their email and password or with their Google,
        Facebook, Micorosft or Github account.
      </p>
      <p className="text-lg text-center">
        You can find the source code for this application on{' '}
        <a
          href="https://github.com/Pumpinator/spring-security"
          target="_blank"
          rel="noopener noreferrer"
          className="text-blue-900 hover:underline"
        >
          GitHub
        </a>
        .
      </p>
      <div className="max-w-md w-full space-y-8">
        <div>
          <h2 className="mt-6 text-center text-3xl font-extrabold">
            Sign in to your account
          </h2>
          <Login />
        </div>
      </div>
    </main>
  );
}
