First of all i have used the MVVM design pattern  
1-I am starting to use databinding but there is a lot of stuff you can't do with databinding yet , but at least it provides a context 
and i want to be used to it when it becomes fully developed.
2-I have used retrofit for network calls , their GsonConverter is so good and with the JSON to kotlin plugin life is easy now.
 -Sometimes i use retrofit with their kotlin koroutines using await and async and letting it return Deffered Json but what i dont like is the try catch so i am sticking with the callback for now.
3-For the depedency Injection i love to use Kodein it is a new light weight Kotlin dependency injection but that s because under the hood it is actually a dependency retrieval container .
  -It is very efficient , the lazy init is just awesome.
4-Well what can i say the Android architeture components are just awesome i have used Android Jetpack Livedata as well as the paging library in this project.
5-Viewmodel and DataSource used in a smooth way with a Viewmodel provider and a DataSource factory.
6-Any tip on my code is appreciated and feel free to contact me for any question i will gladly collaborate Cheers.
7-PS: The api was not returning any repository when using the current date so i have used a static old date.
