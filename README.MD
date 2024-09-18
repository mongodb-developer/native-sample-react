# Notice: Repository Deprecation
This repository is deprecated and no longer actively maintained. It contains outdated code examples or practices that do not align with current MongoDB best practices. While the repository remains accessible for reference purposes, we strongly discourage its use in production environments.
Users should be aware that this repository will not receive any further updates, bug fixes, or security patches. This code may expose you to security vulnerabilities, compatibility issues with current MongoDB versions, and potential performance problems. Any implementation based on this repository is at the user's own risk.
For up-to-date resources, please refer to the [MongoDB Developer Center](https://mongodb.com/developer).


Capacitor Demo app

# Setup

- Install and configure iOS Native SDKs. See the Capacitor environment guide [here](https://capacitorjs.com/docs/v3/getting-started/environment-setup#ios-development)
- Install and configure Android Native SKD. See the Capacitor environment guide [here](https://capacitorjs.com/docs/getting-started/environment-setup#android-development)
- Clone the project
- run `npm install`

# Building iOS

- Run a build of the React app: `npm run build`
- Sync the native project and web project: `npx cap sync ios`
- Open native project: `npx cap open ios`
- Build and deploy to a simulator or native device. Details can be found [here](https://capacitorjs.com/docs/ios#running-in-xcode)


# Building Android

- Run a build of the React app: `npm run build`
- Sync the native project and web project: `npx cap sync android`
- Open native project: `npx cap open android`
- Build and deploy to a simulator or native device. Details can be found [here](https://capacitorjs.com/docs/android#running-with-android-studio)


