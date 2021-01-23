# Spring boot based REST API for witness reporting

## Instructions for local running
./gradlew bootRun    - for Linux  
gradlew.bat bootRun   - for Windows  

For overriding default configuration, you should pass the command line arguments in this format:  
--args='--name1=value1 --name2=value2'  
, where 'name1' is the name and 'value1' is the value of the argument.  

For example, this command (Linux) will start application on the port 8085:
./gradlew bootRun --args='--server.port=8085'

## API consuming
API supports witness report creating operations using HTTP POST method to the resource root (eg http://localhost:8080/api/reports)

Examples of the valid POST JSON body:  
{
    "title" : "VIOLENCE AT THE UNITED STATES CAPITOL",
    "phone" : "+381643333333"
}  

{
"title" : "VANDALISM OF HUNTSVILLE SYNAGOGUES",
"phone" : "+49 (0) 931 111111111"
}

## Output file
By default, file "reports.txt" will be created in the root directory of the project. File path can be changes at startup by changing the "file.path" property. If file doesn't exist, it will be automatically created.

Example of the output file content:  
title=VANDALISM OF HUNTSVILLE SYNAGOGUES, phoneNumber=+49 (0) 931 111111111, countryByPhoneNumber=DE, countryByIpAddress=Unknown  
title=VIOLENCE AT THE UNITED STATES CAPITOL, phoneNumber=+381643333333, countryByPhoneNumber=RS, countryByIpAddress=Unknown

## Known issues
-For local running (when client and API service are both part of the same internal network), calculating of country code from IP address is not possible (service can only fetch internal ip address of client)
