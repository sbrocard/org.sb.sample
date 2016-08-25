h1. org.sb.sample
sample of cordova applications

h2. Build and run the web app 
Go to the webapp directory:

{code}
cd org.sb.sample.push.webapp
{code}


build and run the servlet:

{code}
  ./graddlew jettyRunWar
{code}


h2. Build and the App
You need to have *Cordova* and *Android SDK* already installed and configured.

Go to the cordova directory:
* initialize your project: 
{code}cordova prepare{code}
* run your App on android: 
** plug your android device on your computer
** run
{code}cordova run android{code}
  
h2. Send a notification
* In a web browser go to: http://localhost:8090/org.sb.sample.push.webapp
* Enter the content of your message and click on 'Submit'

Check that your application on your android device has received the message.
