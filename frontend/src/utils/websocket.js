const onMessageReceived = event => {
  const text = event.data;
  console.log(text);
};

export const initializeWebsocket = () => {
  if (window.WebSocket) {
    new WebSocket('ws://localhost:8080/backend/account').onmessage = event =>
      onMessageReceived(event);

    new WebSocket(
      'ws://localhost:8080/backend/notification'
    ).onmessage = event => onMessageReceived(event);
  } else {
    // Bad luck. Browser doesn't support it. Consider falling back to long polling.
    // See http://caniuse.com/websockets for an overview of supported browsers.
  }
};
