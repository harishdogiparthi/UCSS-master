package com.anonymous.ucss.config;


public class Config {



    //Address of our scripts of the CRUD
    //in localhost 10.0.2.2/UnivApp
    public static final String URL_REGISTER="http://10.0.2.2/UnivApp/register.php";
    public static final String URL_LOGIN="http://10.0.2.2/UnivApp/login.php";
    public static final String URL_LIST_ALL = "http://10.0.2.2/UnivApp/list_everyone.php";
    public static final String URL_VIEW_INDIVIDUAL = "http://10.0.2.2/UnivApp/view_individual.php";
    public static final String URL_EDIT_USER = "http://10.0.2.2/UnivApp/edit_profile.php";
    public static final String URL_UPDATE_USER = "http://10.0.2.2/UnivApp/update_user.php";

    public static final String URL_SEND_STU_SMS= "http://10.0.2.2/UnivApp/stu_sms.php";
    public static final String URL_SEND_STU_MAIL = "http://10.0.2.2/UnivApp/stu_mail.php";
    public static final String URL_DEL_USER = "http://10.0.2.2/UnivApp/del_user.php";

    public static final String URL_UPLOAD = "http://10.0.2.2/UnivApp/upload.php";
    public static final String URL_SEND_MAIL = "http://10.0.2.2/UnivApp/test.php";
    public static final String URL_SEND_LECT_SMS= "http://10.0.2.2/UnivApp/lect_sms.php";


    //Keys that will be used to send the request to php scripts
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_TABLE = "table";
    public static final String KEY_USER_WHO = "who";
    public static final String KEY_USER_FNAME = "fname";
    public static final String KEY_USER_MNAME = "mname";
    public static final String KEY_USER_LNAME = "lname";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_PASSWORD = "password";
    public static final String KEY_USER_RNO = "rno";
    public static final String KEY_USER_MNO = "mno";
    public static final String KEY_USER_DOB = "dob";
    public static final String KEY_USER_GENDER = "gender";
    public static final String KEY_USER_HOBBIES = "hobbies";
    public static final String KEY_USER_MAIL_SUBJECT = "mail_subject";
    public static final String KEY_USER_MAIL_MESSAGE = "mail_message";
    public static final String KEY_USER_SMS_UID = "sms_uid";
    public static final String KEY_USER_SMS_UPASS = "sms_upass";
    public static final String KEY_USER_SMS_NUMBER = "sms_number";
    public static final String KEY_USER_SMS_MESSAGE = "sms_message";
    public static final String KEY_USER_MAIL_TO = "mail_to";




    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_RNO = "rno";
    public static final String TAG_FNAME = "fname";
    public static final String TAG_MNAME = "mname";
    public static final String TAG_LNAME = "lname";
    public static final String TAG_MOB_NO = "mob_no";
    public static final String TAG_NAME = "name";
    public static final String TAG_DOB = "dob";
    public static final String TAG_ID = "id";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_SUB1 = "sub1";
    public static final String TAG_SUB2 = "sub2";
    public static final String TAG_SUB3 = "sub3";
    public static final String TAG_SUB4 = "sub4";
    public static final String TAG_SUB5 = "sub5";



    //employee id to pass with intent
    public static final String USER_ID = "emp_id";
    public static final String login_id = "login_id";
    public static final String list = "list";
    public static final String table = "table";
    public static final String WHO = "MASTERS";
    public static final String MASTERS = "MASTERS";
    public static final String LABEL1 = "Internals";
    public static final String LABEL2 = "Attendance";

    public static final String SPRING_INTNL = "Spring_intnl";
    public static final String SUMMER_INTNL = "Summer_intnl";
    public static final String FALL_INTNL = "Fall_intnl";

    public static final String SPRING_ATTEND = "Spring_attend";
    public static final String SUMMER_ATTEND = "Summer_attend";
    public static final String FALL_ATTEND = "Fall_attend";



}
