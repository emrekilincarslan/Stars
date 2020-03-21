# Stars
Android Architecture Components and libraries used
- Dagger
- Room
- Navigation
- Retrofit
- RxJava 
- Lottie
- Mockito (Testing)
- Material
What does this project do?
displays the stars name  in a recyclerview.

 MVVM and RX. Also,used  Architecture Components, a set of libraries which help you build a robust, testable app using a MVVM 
 architecture.
 
The View :
Activity / Fragment / View
Query relevant UI data from the ViewModel
Query the ViewModel to perform operations on the Data (insert/edit data based on user input )


The ViewModel :
it is a connection between the View and the Model
Queries/aggregates data from the Model, and transforms it for the View
Expose means for the View to update the Model


The Model :
means DataModel / Repository
Holds the business logic
gives data from  sources (DB, REST Api, cache)



What is disadvantages of MVP ?
Too many interfaces
Presenter is doubled to the View
Usually a change in the View implies changes in the Presenter (and also, adding new methods on the view & presenter interfaces)
Can’t really reuse the Presenter as it is usually coupled to a specific view


Advantagesr in MVVM ?

No more too many interfaces
ViewModel and DataModel still testable using JUnit
ViewModel no longer coupled to a specific View
ViewModels can supposedly be used and/or composed in multiple views (imagine a completely different tablet View that reuses 
multiple ViewModels that have been used so far independently on some phone Views)


I havent used architecture components becouse  ?
they were still in alpha,might not be stable
The libraries are not open source
I want to keep my architecture clean, as simple as possible, be in control of it and understand what’s happening under the hood 
(just look at the ViewModelProviders.class of the library; dig down in the source code, and you’ll see Fragments are used to 
retain ViewModels; things look error prone and I’d rather build & inject my ViewModels using RX & Dagger and keep everything 
as simple as I can - KISS principle)
 Architecture Components are Lifecycle aware and this can be of great help, but I wasnt needed those features in my
case, and I can easily deal with lifecycle (this might not be the case for you and your apps)
I know LiveData can easily push data updates to multiple observers (which is again really helpful) but I feel this can also 
easily be achieved using a RX PublishSubject
