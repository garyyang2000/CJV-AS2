CJV805 Assignment 1 – Winter 2016
Assignment Submission Form
==========================================================================
I/we declare that the attached assignment is my/our own work in accordance
with Seneca Academic Policy. No part of this assignment has been copied
manually or electronically from any other source (including web sites) or
distributed to other students.
Name(s)   Student ID(s) Seneca LearnID(s)
Ge Yang   112227152     gyang26
Bohao Liu               bliu66
Yan Liu   040693152     yliu439
--------------------------------------------------------------------------
--------------------------------------------------------------------------
--------------------------------------------------------------------------

This assignment provides a package (ca.myseneca.model) to connect database
and operate the employees table. In the ca.myseneca.test package, there is 
the  HRManagement class to test all the method in the model package.
All the methods are also defined in the DAMgrIntf interface, which is 
implemented by DAMgrImpl class. The DAMServer class in the 
ca.myseneca.rmi.server package provide RMI service with all the methods.
A client class called HRManagermentClient in ca.myseneca.rmi.client calls
the services and tests all the methods. 