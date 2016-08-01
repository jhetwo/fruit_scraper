### README

To build: `gradle build`

To run: `gradle run`

The first test written was the integration test, after this time pressure lead to prioritising functional code over test coverage. As such coverage is not as high as it should be.  

At the time of writing known issues include (also see TODO comments):
 - JSON formatting of numbers is not to 2 decimal places
 - Total sum is subject to rounding errors at point of marshalling to json
 - The git commit history is not as informative as I'd intended. In practice with large stories like this my usual approach would be to commit often as I have done with many work in progress commits, and then do an interactive rebase to provide meaningful comments and squash any commits that don't constitute a coherent block of work.  
 
 Improvements:
 - Checked exceptions being rethrown as RuntimeExceptions in a number of places where recover is not feasible given the sparse requirements
