Dependency:  
Spring Reactive Web, Lombok, twilio maven dependency. 

1. config - TwilioConfig. 
2. dto - PasswordResetResponseDto, PasswordResetRequestDto, OtpResponse. 
3. service - TwilioOneTimePassword. 
4. handler - TwilioOtpHandler. 
5. router - TwilioRouter. 

Routes:  

POST : http://localhost:8080/router/sendOTP  
JSON : 
{  
"phoneNumber" : "+1 4155555555",
"username" : "username"
}  

POST : http://localhost:8080/router/validateOTP  
JSON  : 
{
    "username": "username",
    "oneTimePassword": "555555"
}
