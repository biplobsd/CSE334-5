# QuizTime Android App
QuizTime is a simple quiz app that allows users to participate in quizzes. The app utilizes Firestore for storing quizzes in the cloud. Users can create, delete, and test quizzes while also accessing a leaderboard. Firebase authentication is implemented for user registration and quiz creation.

## The major tools used in this project are -
- Firebase Authenticatio
- Firebase Firestore

## APP Try
[Download APK](https://github.com/biplobsd/CSE334-5/releases/download/v1.0/QuizTime-release.apk)

## Usage
Quiz Participation without Account Creation
If you wish to participate in a quiz without creating an account, you will need the quiz ID. After providing the quiz ID, the next screen prompts you to enter your name. Once you have entered your name, you can proceed to take the quiz. Finally, the leaderboard for the specific quiz ID will display your position and score.

## Registration
To create an account, navigate to the registration section. You will need to provide your full name, email address, password, and confirm the password. After successful registration, you will be directed to your dashboard. From the dashboard, you can start quizzes using the quiz ID, create new quizzes, delete existing quizzes, and sign out.

## Quiz Creation
On your logged-in dashboard, you can find a "Create Quiz" button. Clicking on this button allows you to specify the quiz length. Afterward, you can input the questions, options, and select the correct option from a dropdown menu. Upon completion, you will receive a unique quiz ID. This quiz ID can be shared with other users who can then take the quiz and view their position on the leaderboard.

## Firebase data structure 
### Configs total quiz size
![image](https://github.com/biplobsd/QuizTime/assets/43641536/9e013dc7-8fd9-4414-ac63-86953bc4367f)

### Quiz document
![image](https://github.com/biplobsd/QuizTime/assets/43641536/889a35fc-7ea3-4774-86c6-15a75cbffd7b)

### Quiz score document
![image](https://github.com/biplobsd/QuizTime/assets/43641536/0e3d97c7-b2ea-44d9-b147-8c58fe6a50f4)

## Screenshorts
![preview gif](https://github.com/biplobsd/QuizTime/assets/43641536/b664a5af-c1f7-4491-8e34-0e2e3bfa6db1)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/39d5cf4a-4c8c-4084-adec-5304bdd40d67)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/e1b054cd-91ff-41d9-b442-3afc0d71e043)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/6b1078f9-c66f-4d82-9028-88ef1ff3937b)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/843a4445-492e-49ef-affa-0616280f740d)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/307b02ab-e449-4f2a-be4d-074005256c27)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/b200aeb1-51e3-451c-9ed6-314787f5d102)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/d902d54b-575f-4cf8-883f-ff9225da3fc0)
![image](https://github.com/biplobsd/QuizTime/assets/43641536/5d237b2c-16ed-4fe5-bc70-0e467e4e86b1)

# Contributing
Contributions to QuizTime Android App are welcome. If you have suggestions for new features, bug fixes, or improvements, please submit a pull request.

# License
QuizTime Android App is licensed under the MIT License. Feel free to use, modify, and distribute this code for personal and commercial purposes.

