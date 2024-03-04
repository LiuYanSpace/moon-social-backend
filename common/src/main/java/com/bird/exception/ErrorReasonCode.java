package com.bird.exception;

public enum ErrorReasonCode {
    Invalid_Username_Password, // username or password is incorrect for login
    Duplicated_Username, // username is already used when creating a new user
    Duplicated_UserEmail, // username is already used when creating a new user
    Doorkey_Wrong, // service partner is already created
    Doorkey_Max, // service partner is already created
    Not_Allowed_User, // the user is not allowed to call the specific API
    Not_Found_Entity, // the entity the caller tries to update/delete doesn't exist
    Invalid_Reset_Key, // the token for password reset is not valid
    Size_Limit_Exceeded, // max size of files to upload exceeds 10mb
    Server_Error, // unexpected server error
    ACCESS_Denied

}
