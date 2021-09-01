# What was used

Made with Kotlin with MVVM architecture pattern, using Retrofit2 for the services, and View Binding to get the views.
Also basic unit testing for a method converting money to see if it did it right, used Junit.

# To Improve: Code wise

To reuse filtered transactions list in DataHandler (ViewModel).

Add more tests for DataHandler.

Better REST error handling: Specify which rest failed.


# To Improve: Functionality wise:

Let the user choose the currency in which they want to see the sum of the transactions. The methods in
ViewModel's DataHandler are ready for that, since they take the currency as a parameter,
the Euro conversion is not hardcoded.


