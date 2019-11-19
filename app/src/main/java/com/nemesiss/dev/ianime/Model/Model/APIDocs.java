package com.nemesiss.dev.ianime.Model.Model;

public class APIDocs {
    public static final String DeploymentAddress = " http://server.ip/ ";

    public static final String Login=DeploymentAddress+"/user/login";
    public static final String Register=DeploymentAddress+"/user/register";
    public static final String Logout=DeploymentAddress+"/user/logout";
    public static final String GetAccountInfo=DeploymentAddress+"/user/profile?userid=";
    public static final String UpdateAccountInfo=DeploymentAddress+"/user/profile";
    public static final String UploadAvatar=DeploymentAddress+"/user/avatar";
    public static final String GetMyFollowing=DeploymentAddress+"/user/following";
    public static final String FollowOrCancel=DeploymentAddress+"/user/follow";
    public static final String GetMyFollower=DeploymentAddress+"/user/follower";
    public static final String LikeOrCancel=DeploymentAddress+"/illustration/mylike";
    public static final String GetWorkDetails=DeploymentAddress+"/illustration/sketchwork?&id=";
    public static final String GetPopularSketch=DeploymentAddress+"/illustration/favorite_sketch";



}
