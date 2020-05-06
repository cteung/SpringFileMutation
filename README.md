# Spring File Mutation
Build a Spring Boot 2+ Service using Maven that consumes a xml file(see `data.xml`) and returns a response containing information about the individual entries in the xml file.

## Information 
The `data.xml` is broken into individual sets of information identified by the `</PersAutoPolicyQuoteInqRq>`tag.   Assume this solution will be "production ready" and should contain propper logging and test coverage. Keep in mind that xml file sizes may varry so scalability should be considered.

The xpaths to build the responses can be found bellow : \
Policy Number - `//PolicyNumber`\
Customer Name - `//InsuredOrPrincipal[InsuredOrPrincipalInfo/InsuredOrPrincipalRoleCd='Insured']/GeneralPartyInfo/NameInfo/CommlName/CommercialName`\
Policy Type - `//PersPolicy/LOBCd`\
Total Premium - `//PersPolicy/CurrentTermAmt/Amt` \
Vehicle Collection - `//PersAutoLineBusiness/PersVeh`\
Driver Collection - `//PersAutoLineBusiness/DriverInfo`


## Response Format 
The response should contain an array of the following information extracted from the file and a count of the number of entries: 
```json
{
"policyNumber": String,
"customerName": String,
"PolicyType": String,
"totalPremium": Float,
"vehichles": [{
"make" : String,
"model": String,
"modelYear": String
}...],
"drivers" : [{
"driverName": String,
"age": Integer
}...]
```
