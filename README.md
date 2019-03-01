# mse-fdpsm-android-movies
MSE FDPSM Exercise

* NavigationController
* Kotlin
* Safeargs

# Functional Requirements
  * Create an application containing an Activity and three Fragments
  * The first Fragment must contain a RecyclerView containing 
  a list of movies and a field that will serve as a live search filter.
  * When typing 2 or more letters a search is conducted and the 
  results are posted shown inside the RecyclerView (API: http://www.omdbapi.com/?t=movie+title)
  * The second Fragment opens when any of the search results is 
  pressed and contains detailed information as well as the movie poster
  * Allow creating a watchlist / favourite list using towatch / favourite buttons placed in the app bar
  * When pressing the movie poster, the third Fragment opens 
  that shows the movie poster full screen
  
  # Non-functional Requirements
  * Use Java or Kotlin to implement the application
  * Do not bind to the real API yet, use mock data instead but 
  make sure to immitate the API response
  * The towatch and favourite button are NOOP for now, don’t 
  worry about implementing them just yet ;)
  * Implement an app bar using the Toolbar implementation
  * Use the Navigation component to craft the navigation within your app
  * Make sure to use safe args to transfer data between the Fragments
  * Link the Toolbar to your application’s navigation
  * Make sure you make use of RecyclerViews recycling facilities
  * You may use Picasso to manage your image downloads
  * Use github to manage your code:
  * TAG THE RESULT OF THIS EXERCISE AS 1.0.0
