# Assessment Android project for Midtronics

### Task
- Create a profile of yourself. At minimum include your name, a picture and a scrollable summary of your education and work experience. 
- Create a screen that contains a list of countries: 
a. A list of countries can be found here https://github.com/vinaygaba/Ultimate-String-Array-List/blob/master/Countries.xml
- When a country is selected transition to a country detail screen.
a.	Data for the detail screen should be retrieved by making an HTTP GET to https://restcountries.com/v3.1/name/{countryName}  where “{countryName}” is the name of the country from the list (ex. https://restcountries.com/v3.1/name/Argentina).  Full API details can be found at https://restcountries.com/#api-endpoints-v3-all
b.	The country detail screen should show the name of the country, its capital, population, area, region and sub-region.

# Brief result overview

![](Demo.gif)

# Implementation description

## High-level overview
From the architectural point of view, the application build based on Layered Architecture. In our specific case, three layers were selected - presentation, domain, and data layer. Each of these layers is in a separate package. So instead of making separate Graggle modules for each layer, this app is a single Graggle module-based to keep it simpler. 

- Presentation layer is located on `ui` package and represented by MVVM architecture pattern. XML layouts approach with Fragmenta and Activity was used instead of JetPack Compose. UI has been split into two parts using Bottom Navigation - profile for the task (1) and countries for the task (2). 

- The domain layer is located on `domain` package and it's built around the Transaction Script approach. Where each Business Scenario is wrapped into its own Use Case, implemented as a Command pattern. Because the application is small, Transaction Script is preferable then Domain Model or Table Model. 

- The data layer is located on `data` package and represented by data access classes. This is a place where assets and api data providers located


>  To keep this project simple, Dependency Injection was made by using Koin - light Kotlin dependency injection library.


### Presentation layer
In the app single Activity was used. Inside of which NavController is using to simplify transactions between Fragments without FragmentManager and handling back navigation manually. As was said before this layer is built with Android Architecture Components. All presentation logic like fetching data, and mapping it from the domain format into UI-related format, is located in View Model. View Model is spreading data to the Fragment. To link View Model and Fragment togetger UI State patter was used wrapped into Kotlin Flow. 

### Domain layer
Using the Business Scenario paradigm, two Use Cases were created. Just to demonstrate, adding more Domain Logic is just simply customizable by adding a new Use Case class. Each Use Cases is customising by data provider which is injecting into Use Cases constructor. `CountriesUseCase` doesn't have any data provider to show the alternative way. 

### Data layer
Represented by set of different data provider classes and data classes. One for workig with API request, the other one is for working with data from the assets.
