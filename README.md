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

3. ~~As a user I want to be able to have less distraction so my mind can be forced to focus on the task~~

4. As a user I want to have a short-term goal so that can give me more motivation to do my work 

**5. As a user, I would like to have more options to determine how I would want to approach my given task.**

**Features – Phone Application(Android):** 
1. To-Do List 
   User will able to insert the tasks that they want to accomplish and delete it ( automatically?) when they are done with      it.
   * **UPDATE:** Haven’t been implemented yet. We knew how to write an input and save it in form of list which can be in form of array/arraylist ( Array is preferable). We are yet to find a way to enable the app to save the data locally.
   * **UPDATE 2:** To-Do List has been implemented. At the beginning( when entering the activity for the first time) Chris have provided a quick instruction on how to use it. It won't appear again the next time the activity is called. This should fulfill the requirement for vostok of creation, retrieval(which should be sufficient just by showing the list of things to do), and deletion of record. The to-do list will be cleared in a duration of a day. At the same time, the app will notify the user of his performance of that day.

2. Timer to decide on the time spent to do the specified tasks for that instance.
   * **UPDATE:** Countdown implemented successfully, though we are still planning to refine the input method and the clock presentation itself. Instead of Keyboard, we would want to provide the users with a dialog containing list of numbers. For the Clock, we are going to change the format so it will still show 2 digits even when it’s on a single digit count ( doable with decimalformat class). 
   * **UPDATE 2:** Due to the obstacle we faced, we have decided to tweak the functionanility of the timer. Instead of turning off wifi and data while the timer is active, we have decided to create a work-break cycle. The users can choose from an array of pre-determined timing and number of cycles. The timer is also programmed to prevent certain situation from occuring such as having a break period when there is only one cycle (hence the break period is useless) or having 0 break period when the cycle number is bigger than 1(In this case the timer will shoot a dialog notification  box to check whether this is a deliberate user's decision). 
*  3. Control over access to phone’s functions.
   ~~This feature is further divided to two parts:~~ 
   * ~~Prohibit internet/ wi-fi connectivity ~~
~~Turning off the internet connection or forced airplane mode is the main features that my friend and I want to implement. With what can be done with internet connections, we label it as the main source of distraction when it comes to gadgets. So we want our app to cut the internet connection during the designated period to **limit** the options of things the user can do so they will not be easily distracted and is **forced** to focus on the task. If possible, we aim to lock the users to the timer screen like the one shown in the mock-up, with only small windows access to the phone which will be explained next~~

   * ~~**UPDATE:** manage to set up a way to consistently disable wifi when the timer has started. We are looking to improve it so that the wi-fi disabler function can works even when the application is minimized. Additionally we plan to send a quick notice to let them know when the wi-fi is disabled first time or when they try to turn on the wifi. Our plan is to set up this method as a service that can be run in the  background. Instead of checking the wi-fi state every second, we plan to make it such that it is able to intercept any command to turn on  the wi-fi and switch it off again immediately.~~
   * **IMPORTANT UPDATE:** We have just realized that the method to gain access to configure mobile data has been deprecated for the third party developer by Android itself for security reason. We initially wanted to use airplan mode instead in place of mobile data/wifi control, only to find out that the method to access it has been deprecated as well for the same reason. Hence we have no choice but to abandon this user story/features and change the direction in which our app is heading. 

 

   * ~~Selective  access to other applications~~
~~People have different means to help them stay focus. Some requires music, while others require others to track their progress. So we still want to open up access to functions that we deem helpful to push themselves when doing their tasks.~~

   * ~~**UPDATE:** Haven’t been implemented yet, although we know the android’s capability to start another app from one app. Although we don’t know yet if we can block user’s access to another app.~~

* 4. Short term goal in term of Daily evaluation and Work/Break interval.
* **UPDATE 2:**We would like to set up a daily report which will inform the user of how many tasks the user has managed to and has failed to complete in a span of one day. This, we believe, will provide the users a better idea of how many things he can do in a day and help them to set a more achievable goal. Perhaps this will even allow them to challenge themselves to be able to finish more tasks. The timer will also help them to focus their mind by providing an instant short-term goal in form of break duration.  
* 
*5. **UPDATE:**made possible with the current timer. The users can still choose to have no break duration by leaving the break duration at 0, or select the cycle option as 1. 

**Optional Features**
  Achievement Feature 
  Push factors – short term reward for using the app and pushing themselves to not be distracted ( effectiveness is definitely not guaranteed).
  ** UPDATE:** If we have time we will implement it after submission of milestone 3  

**Optional Goals**
IOS app 
Web app
* **UPDATE:** IOS app is cancelled because of the limitation of accessibility that the software  gave us. The web app might not be implemented due to time constraint and from the mentor suggestion that our app might be more suitable for a mobile app
