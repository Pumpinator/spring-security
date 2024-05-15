export function request(options) {
  const headers = new Headers();

  if (options.contentType) {
    headers.append('Content-Type', options.contentType);
  }

  if (window.localStorage.getItem('accessToken')) {
    headers.append(
      'Authorization',
      `Bearer ${window.localStorage.getItem('token')}`
    );
  }

  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);

  return fetch(options.url, options).then((response) => {
    return response.json().then((data) => {
      if (!response.ok) {
        return Promise.reject(data);
      }
      return data;
    });
  });
}

export function triggerFacebookLogin() {
  window.fbAsyncInit = function () {
    FB.init({
      appId: appId,
      cookie: true,
      xfbml: true,
      version: 'v12.0',
    });
  };
}
