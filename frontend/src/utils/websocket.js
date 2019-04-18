export const initializeWebsocket = setNotificationMessage => {
  if (window.WebSocket) {
    new WebSocket('ws://localhost:8080/backend/account').onmessage = event =>
      setNotificationMessage(event.data);

    new WebSocket(
      'ws://localhost:8080/backend/notification'
    ).onmessage = event => setNotificationMessage(event.data);
  } else {
    // Bad luck. Browser doesn't support it. Consider falling back to long polling.
    // See http://caniuse.com/websockets for an overview of supported browsers.
  }
};
