# This is a repository for 🎥 [Test Double presentation/video](https://www.youtube.com/watch?v=rh0k_K7EOAE)

## Test Double
Definition of Test Double is almost as old as my master’s degree and can be found in this [book](https://www.amazon.com/xUnit-Test-Patterns-Refactoring-Code/dp/0131495054). Test Doubles are usefull to implement reliable and fast Unit Tests isolating [System Under Test](https://en.wikipedia.org/wiki/System_under_test) from external dependencies.

## Test Double types
* Dummy objects - used to fulfil consturctor or method arguments list, passed but not used otherwise 
* Stubs - return prepared answers to calls made during the test 
* Spies - are stubs enchanced with recording capability, can record method calls or information about system events like number of messages sent
* Mocks - on top what spies can do mocks are pre-programmed with expectations of return values, can throw exceptions for not supported scenarios and are check during verification for interaction correctnes
* Fake objects - most sophisticated dummy, this can be special implementation not suitable for production that can be used in testing for example: in memory cache, in memory database ...

## Resources for further reading
* [test-doubles-fakes-mocks-and-stubs](https://blog.pragmatists.com/test-doubles-fakes-mocks-and-stubs-1a7491dfa3da)
* [test-doubles-mockito](https://dzone.com/articles/test-doubles-mockito)
* [Test_double](https://en.wikipedia.org/wiki/Test_double)
* [Martin Fowler TestDouble](https://www.martinfowler.com/bliki/TestDouble.html)

### Trivia:
Test Double act just like movie stunt double - replacing "real" code and doing what need to be done :)