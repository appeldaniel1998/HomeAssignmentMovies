# Movies Application
In this home assignment, there were several architectural and technical decisions:
1. Decided to utilize CLEAN architecture and MVI
2. The app is divided into several folders, some of which may seem unnecessary since they only contain 1 file, but I wanted to make sure that the application is "future proof" and any extensions made to it are as simple as possible in terms of placement.
3. In some places (such as di), I have separated the logic relevant to the "feature" which I was tasked to implement and the logic relevant to the whole application into separate files and/or folders.
4. I have decided to use a single scaffold for the entire application, changing only its content and parameters, for 2 reasons: maintain code as simple as possible and optimize the application. Its logic is contained within the _core.presentation.scaffold. It has its own viewmodel, state and event classes.
5. Used Launched Effects to initialize Scaffold parameters (name of screen and whether the back button is visible in the top app bar) in the 3 screens of the app
6. The util folders contain files which are pertinent to the scope where the folder is located.
7. Used retrofit for HTTP requests and caching of calls for 24 hours (for optimization)
8. Used Coil for image loading and caching (for 24h as requested)
9. Used Android YouTube Player to enable in-app playback of the movie trailers
10. Used MutableStateOf methods, as well as the @Immutable annotation to optimize recompositions (verified through the layout inspector)
11. Extracted all UI strings into the strings.xml for future internationalization
12. Provided error handling for when network or other errors occur using snack bars
13. Used a shared viewmodel between the three main screens of the app, to avoid large object transfers through compose navigation

Future features to implement:
1. The saved movies currently are persistent to a single run of the application. Maybe a better but a more complex implementation would be to locally save this information to persistent storage for future use, or save it in a cloud storage of some sort
2. Unit testing, of course, is necessary for production level applications
3. The UI which I implemented is quite simplistic, I would spend time to make it more "visually attractive" if this application was to be released.
4. The YouTube player currently does not allow for full screen viewing through the app (only through YouTube ). This, too, would be remedied if more time was spent on the project.  
 