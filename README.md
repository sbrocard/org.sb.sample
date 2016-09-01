# org.sb.sample
sample of cordova applications that receives push notification sent by a java web application

## Build and run the web app 
Make sure you are in root directory:

    cd org.sb.sample

build and run the servlet:

    ./graddlew jettyRunWar

## Build and the App
You need to have *Cordova* and *Android SDK* already installed and configured.

* Go to the cordova directory:

    cd  org.sb.sample.push.cordova
    
* initialize your project: 
    
    cordova prepare
    
* run your App on android: 
    * plug your android device on your computer
    * run

    cordova run android
  
## Send a notification
* In a web browser go to: [http://localhost:8090/org.sb.sample.push.webapp](http://localhost:8090/org.sb.sample.push.webapp)
* Enter the content of your message and click on 'Submit'

Check that your application on your android device has received the message.
