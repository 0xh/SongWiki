# SongWiki frontend
This folder contains the SongWiki frontend source code.  
The frontend is built with React and bootstrapped by [create-react-app](https://github.com/facebook/create-react-app)

## :shield: Authentication
Authentication is a core part of the application. It is therefor important to understand how and with what it is implemented.

In order to keep the authentication state throughout the application I opted for the [React Context API](https://reactjs.org/docs/context.html). This API removes the extensive [prop drilling](https://kentcdodds.com/blog/prop-drilling) that has to be done otherwise to pass state to components that are nested deeply. Compared to the popular state management tool [Redux](https://redux.js.org/) it has a few advantages for this specific project:

1. There isn't much global state to keep ahold of. Redux is overkill.
2. The Context API comes standard with React since version 16.3. No extra libraries needed!

The specification for the provider and consumer can be found in ```src/utils``` and are implemented in the root index and the needed components respectively.

## :speech_balloon: Communication
The frontend of SongWiki uses multiple ways to communicate with the backend:

1. REST endpoint calls: used for general information
2. WebSockets: used for real-time communication like notifications

## :wrench: Setup
For local development to work correctly between frontend and backend the package.json file contains a `proxy` property. This property makes sure to redirect any unknown requests to the specified URL. This comes in handy when calling the backend API and makes sure CORS errors don't exist.  
[Source](https://facebook.github.io/create-react-app/docs/proxying-api-requests-in-development)

## :page_with_curl: Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br>
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.<br>
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### Advanced functionality
Please see the create-react-app documentation for info on  advanced functionality like eject etc.