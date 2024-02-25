package com.bird.exception;

public enum ErrorReasonCode {
    Invalid_Username_Password, // username or password is incorrect for login
    Duplicated_Username, // username is already used when creating a new user
    Duplicated_UserEmail, // username is already used when creating a new user
    Duplicated_ServicePartner, // service partner is already created
    Not_Allowed_User, // the user is not allowed to call the specific API
    Not_Found_Entity, // the entity the caller tries to update/delete doesn't exist
    Invalid_Reset_Key, // the token for password reset is not valid
    Size_Limit_Exceeded, // max size of files to upload exceeds 10mb
    Server_Error, // unexpected server error
    Favourite_Supplier_Not_Exist,
    Favourite_Supplier_Already_Update,
    Supplier_Not_Found,
    Favourite_Product_Already_Exists,
    Favourite_Product_Already_Delete,
    Custom_Product_Already_Delete,
    Custom_Product_Not_Found,
    Cannot_Modify_Owner,
    Order_Already_Delete,
    Order_Cannot_Update,
    Order_Cannot_Found,
    User_Not_Found,
    Company_Not_Found,
    Email_Send_Fail,
    Email_Template_Create_Fail,
    Email_Template_Exist,
    Duplicated_Company_Email,
    Duplicated_Company_Name,
    Company_Not_Verify,
    Invalid_Parameters,
    ACCESS_Denied,
    Not_File,
    Invalid_File_Type,
    Invalid_CSV_FILE,
    Product_FORMAT_ERROR,
    Property_Not_Found,
}
