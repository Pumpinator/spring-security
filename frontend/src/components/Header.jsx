import reactLogo from '../assets/react.svg';
import viteLogo from '../assets/vite.svg';
import tailwindcssLogo from '../assets/tailwindcss.svg';

export function Header() {
  return (
    <header className="flex gap-8 items-center justify-center text-center">
      <p>
        application built with React, Vite and Tailwind CSS.
      </p>
      <div className="flex gap-4 items-center justify-center text-center">
        <img
          src={reactLogo}
          className="hover:scale-110 transition-all duration-200 ease-out"
          alt="react logo"
        />
        <img
          src={viteLogo}
          className="hover:scale-110 transition-all duration-200 ease-out"
          alt="vite logo"
        />
        <img
          src={tailwindcssLogo}
          className="hover:scale-110 transition-all duration-200 ease-out"
          alt="tailwindcss logo"
        />
      </div>
    </header>
  );
}
