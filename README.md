# org.sb.sample
sample of cordova applications

## Build and run the web app 
Go to the webapp directory:

    cd org.sb.sample.push.webapp

build and run the servlet:

    ./graddlew jettyRunWar

## Build and the App
You need to have *Cordova* and *Android SDK* already installed and configured.

Go to the cordova directory:
* initialize your project: 
    
    cordova prepare
* run your App on android: 
** plug your android device on your computer
** run

    cordova run android
  
## Send a notification
* In a web browser go to: [http://localhost:8090/org.sb.sample.push.webapp](http://localhost:8090/org.sb.sample.push.webapp)
* Enter the content of your message and click on 'Submit'

Check that your application on your android device has received the message.
