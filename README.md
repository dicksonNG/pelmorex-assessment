# Pelmorex-assessment
Pelmorex Corp assessment

## Prerequisites
Before you begin we recommend you

    -Android Gradle Plugin Version : 4.1.3
    -Gradle Version: 7.4
    -Min SDK Version: 29
    -Target SDK Version: 32

## Build and run application
Please clean and rebuild or invalidate cache of the project if build failure. If have any questions, please feel free to contact me.    

## Project Component

The development language used Kotlin. The project has 3 main pages: Weather Page, Gallery Page, and Contact Us Page. Also, MVVM is the architecture for the app.  

Weather Page: To show the 5 city list for the user to select which displays city weather information from api. Also, the user can switch the temperature scale to Celcius or Fahrenheit from the switch button.  
Gallery Page: To show 10 image galleries, able to tap to enter the full screen for the image and able to zoom the image.  
Contact Us Page: For user input personal information each input field has some validation and used regex expression to check it. If the validation is not passed, the send button will be dimmed, not allowing the user to click.  
    -all field cannot be empty  
    -name cannot contain numbers and must be at least 4 characters long  
    -email address must be appropriate email address formatting  
    -phone number must only contain numbers  

## Technical Used

    Navigation: The main component for the app to transfer the fragment.  
    Hilt: The app used Hilt for the Dependency Injection.  
    LiveData: The app used livedata to observe data.  
    Data Binding: Used to bind app UI components  
    Coroutines: The main component for the app to synchronous or non-blocking programming.  
    Retrofit & okHttp: Used to call the API and get back the response from API.  
    Coil: For the application to load the image.   
    Junit: Unit test for the application.  
    Robolectric: Unit test for the application.  

## Unit Test Case

The application has 2 test classes ContactUsFieldValidTest and FragmentTest.  

ContactUsFieldValidTest:  The purpose of this class is to test different cases whether the user input field is valid.  
FragmentTest:  The purpose of this class is to test whether each fragment field is not null.   
