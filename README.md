# Shipping Cost Estimator

## Introduction

The Shipping Cost Estimator is a program built with a microservice architecture that allows users to estimate the cost of shipping an item from one destination to another. The user is able to specify the package dimensions, the shipping source, and the shipping destination. Given this information, the program returns an estimated cost for shipping the item, from various shipping providers.  

The Shipping Cost Estimator is accessible through REST API endpoints as well as a user interface.

## Storyboard

[Storyboard - on InVision](https://projects.invisionapp.com/prototype/PackageEstimation-ckf98cvt2004taz0128ve02vh/play/67bb3bf9)

## Functional Requirements

As someone looking to have packages shipped to different addresses, I want to be able to estimate my shipping costs, so that I will be able to form an accurate budget.  

Example  
Given: A set of package dimensions  
When: The user or service selects a carrier  
When: The user or service selects a predefined package that fits these dimensions  
Then: The user or service will be able to calculate the cost of shipping this package to an address of their choosing.  

Example  
Given: Shipping data are available  
When: The user or service enters an address that does not exist  
Then: The Shipping Cost Estimator will not accept the address, and will not calculate a shipping cost.  

Example  
Given: Shipping data are available  
When: The user or service has selected a carrier and a predefined package  
When: The user or service has entered the address, zip code, and country that they wish to send the package to  
Then: In the web interface, the Shipping Cost Estimator will display the data that the user has entered so far, for confirmation.  

Example  
Given: Shipping data are available  
When: The user has entered the required information and confirmed that it is correct  
Then: The Shipping Cost Estimator will display the estimated cost of shipping the specified item to the specified address.  

Example  
Given: The user has entered the required information and received the estimated cost  
When: The user selects the option to calculate another package in the web interface  
Then: The Shipping Cost Estimator will return to the first page, allowing the user to calculate the estimated cost of another shipment.  

## Class Diagram
![EntAppDevUML (1)](https://user-images.githubusercontent.com/55462414/93695181-0021f600-fae2-11ea-873a-2110b72ba915.png)
## Class Diagram Description
PackageDetails: Responsible for identifying the carrier and predefined package, which in turn identifies dimensions.  
Destination: Responsible for identifying the "to" and "from" locations of the shipment.  
CalculateCost: Determines the service level and the subsequent rate cost of shipping the package.  
ShippingCostEstimatorController: Allows for the ability to find previous estimations and create new ones, saving them to a database.  
ShippingCostEstimatorApplication: UI centerpiece for the application.  
CalculateShipmentCost: UI pages for calculating the shipment cost in the DTO.  
IShippingCostService: Interface for finding and saving shipping estimate objects.  
ShippingCostServiceStub: Stub for IShippingCostService, which includes built-in data to ensure that the interface works properly.  
ShippingCostServiceImpl: Implementation for IShippingCostService, allowing for interaction with the database.  
IShippingCostDAO: Interface for interacting with the database interaction classes.  
ShippingCostDAO: Implementation for IShippingCostDAO.  
ShippingCostNetworkDAO: Low-level database interaction class.  
## JSON Schema
>{
>  "type" : "object",
>  "properties" : {
>    "serviceLevel" : {
>      "type" : "string"
>    },
>    "estArrival" : {
>      "type" : "integer"
>    },
>    "totalCostId" : {
>      "type" : "integer"
>    },
>    "predefinedPackage" : {
>      "type" : "string"
>    },
>    "rates" : {
>      "type" : "integer"
>    },
>    "shipmentName" : {
>      "type" : "string"
>    }
>  }
>}
## Scrum Roles
UI Specialist: Jimmy Tran  
Business Logic and Persistence Specialist: Matthew Willison  
Product Owner / Scrum Master / DevOps / GitHub Administrator: Evan Batsch  

## Milestones

[First Milestone](https://github.com/batschew/shipping-cost-estimator/milestone/1)

## Standup

Weekly standup meetings are held at 5:00 pm on Thursdays on Discord.
