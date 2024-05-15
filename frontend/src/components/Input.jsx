export function Input({ id, name, type, autoComplete, required, placeholder, className }) {
  return (
    <div>
      <label htmlFor={name} className="sr-only">
        {placeholder}
      </label>
      <input
        id={id}
        name={name}
        type={type}
        autoComplete={autoComplete}
        required
        className={`${className} appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-200 bg-black text-gray-200 focus:outline-none focus:z-10 sm:text-sm`}
        placeholder={placeholder}
      />
    </div>
  );
}
