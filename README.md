# CountDown
#### Orbital project 2015

Achievement Aim: Vostok( Beginner Level)  - Might switch to Gemini

**1ST MILESTONE**

**IDEATION**

**Target Audience:** Students/ People who are easily distracted. 
**Problem Definition/ Summary:** Gadgets such as Smartphone and laptop tend to be very distracting to a lot of people when they do their work. Additionally, we view that the Existing Productivity app lacks the element to push or force people to do the tasks that they have planned. We design our app with this problem in mind.
**Ignition Slide :** 
**Ignition video( From 43:11):** https://www.youtube.com/watch?v=osQjStOAci0&index=1&list=PLllwxvcS7ca4i5_GxjfnNTI86AfKIX7KA\

**PLANNED FEATURES**
**User Stories ( new users stories in bold)**
1. As a user I want to be able to list down all the tasks that need to be done so I can better keep track of what need to be done 
2. As a user I want to be able to set a time limit for each tasks so that I can organize myself for the day better.  
3. As a user I want to be able to have less distraction so my mind can be forced to focus on the task
4. As a user I want to have a short-term goal so that can give me more motivation to do my work 
**5. As a user, I would like to have more options to determine how I would want to approach my given task.**

**Features – Phone Application(Android):** 
1. To-Do List 
   User will able to insert the tasks that they want to accomplish and delete it ( automatically?) when they are done with      it.
   * **UPDATE:** Haven’t been implemented yet. We knew how to write an input and save it in form of list which can be in form of array/arraylist ( Array is preferable). We are yet to find a way to enable the app to save the data locally.

2. Timer to decide on the time spent to do the specified tasks for that instance.
   * **UPDATE:** Countdown implemented successfully, though we are still planning to refine the input method and the clock presentation itself. Instead of Keyboard, we would want to provide the users with a dialog containing list of numbers. For the Clock, we are going to change the format so it will still show 2 digits even when it’s on a single digit count ( doable with decimalformat class). 
3. Control over access to phone’s functions.
   This feature is further divided to two parts: 
   * Prohibit internet/ wi-fi connectivity 
Turning off the internet connection or forced airplane mode is the main features that my friend and I want to implement. With what can be done with internet connections, we label it as the main source of distraction when it comes to gadgets. So we want our app to cut the internet connection during the designated period to **limit** the options of things the user can do so they will not be easily distracted and is **forced** to focus on the task. If possible, we aim to lock the users to the timer screen like the one shown in the mock-up, with only small windows access to the phone which will be explained next

   * **UPDATE:** manage to set up a way to consistently disable wifi when the timer has started. We are looking to improve it so that the wi-fi disabler function can works even when the application is minimized. Additionally we plan to send a quick notice to let them know when the wi-fi is disabled first time or when they try to turn on the wifi. 

 Our plan is to set up this method as a service that can be run in the  background. Instead of checking the wi-fi state every second, we plan to make it such that it is able to intercept any command to turn on  the wi-fi and switch it off again immediately. 

We haven’t had the time to do the same thing with regards to mobile data unfortunately.

   * Selective  access to other applications
People have different means to help them stay focus. Some requires music, while others require others to track their progress. So we still want to open up access to functions that we deem helpful to push themselves when doing their tasks.

   * **UPDATE:** Haven’t been implemented yet, although we know the android’s capability to start another app from one app. Although we don’t know yet if we can block user’s access to another app.

**Optional Features**
  Achievement Feature 
  Push factors – short term reward for using the app and pushing themselves to not be distracted ( effectiveness is definitely not guaranteed).

**Optional Goals**
IOS app 
Web app
* **UPDATE:** IOS app is cancelled because of the limitation of accessibility that the software  gave us. The web app might not be implemented due to time constraint and from the mentor suggestion that our app might be more suitable for a mobile app
