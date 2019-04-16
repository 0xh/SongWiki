export const initializeWebsocket = () => {
  if (window.WebSocket) {
    const ws = new WebSocket('ws://localhost:8080/backend/account');
    ws.onmessage = event => {
      const text = event.data;
      console.log(text);
    };
  } else {
    // Bad luck. Browser doesn't support it. Consider falling back to long polling.
    // See http://caniuse.com/websockets for an overview of supported browsers.
    // There exist jQuery WebSocket plugins with transparent fallback.
  }
};
