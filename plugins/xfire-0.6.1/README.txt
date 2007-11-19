XFire plugin for Grails 
=======================

(c) 2007 Chanwit Kaewkasi

This is an XFire plugin for Grails 0.4-SNAPSHOT. 
This plugin allows you to develop Web Services using Grails' service classes.

Installation
============
Type this command in your Grail application directory:
$> grails install-plugin xfire

or manually download, then type:
$> grails install-plugin /path/to/grails-XFire-0.6.zip


Dependencies
============
This snapshot includes some dependency jar file.
If it cannot run out-of-the box, please let me know.

Getting Started
===============
This plugin detects the static property "expose" of a service class.
If the "expose" contains 'xfire' keyword, 
then the plugin expose the service as a Web service.

For example, type this:

$> grails create-service Test

You will get "TestService.groovy" in your service directory.
Then, declare the static property "expose" and you may have some methods like:

class TestService {
  
  static expose=['xfire']

  def boolean serviceMethod(YourDomainClass dc){  
    return true;  
  }
  
}

After running the command "grails run-app",
you can access the WSDL of this example from

http://127.0.0.1:8080/<app>/services/test?wsdl

Please note that TestService.groovy becomes 'test' in WSDL.

TODO List
=========
- implement proper unit tests
- fix bugs in Spring Bean Builder
x support Web Service headers
- implement "onChange" (no idea about it this moment)
- 'before' interceptor, thru Groovy-AOP
  
Version History
===============
v. 0.6.1
- bug fixed
- added xerces-2.8.1.jar into the distribution
v. 0.6
- updated against Grails 0.6

v. 0.5.2
- support service invocation thru Groovy-MOP
v. 0.5.1
- tested against Grails 0.5.6
- support annotation-based service thru Groovy 1.1
v. 0.5
- updated with the new Artefact API
- tested against Grails 0.5
- updated XFire to 1.2.6

v. 0.4-Released
- test against Grails 0.4

v. 0.4-M4
- fixed detection of the transactional property (again)
- improved skipping of all "Groovy" properties
- skipped a java.lang.Boolean property (with "get" prefix !)

v. 0.4-M3
- changed convention
  (using "static expose=['xfire']" instead of the "XFireService" suffix)
- changed URI
  (now it's http://127.0.0.1:8080/<app>/services/test?wsdl for "TestService.groovy")
- the jar name is going to be SNAPSHOT until Grails 0.4 released.

v. 0.4-M2
- updated XFire to 1.2.4
- fixed detection of the transactional property
- fixed detection of "metaMethods"
- refactored

v. 0.4 
- added support for Grails domain classes
- changed the version number to be compatible with Grails
- first public release

v. 0.1
- initial