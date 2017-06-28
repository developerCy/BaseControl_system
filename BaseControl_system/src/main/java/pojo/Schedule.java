package pojo;

import java.util.Date;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public class Schedule
{
    private static final long serialVersionUID = 1L;
    private String id;//ID
    private String title;//日程内容
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String allDay;//是否全天，1 - 是，0 - 不是
    private String color;//颜色
    private String userID;//用户ID
    private java.lang.String isFinish;//是否完成，1 - 是，0 - 不是
    private String createTime;//创建时间
    private String user_name;//外键——用户

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public java.lang.String getId()
    {
        return this.id;
    }
    public void setId(java.lang.String id)
    {
        this.id = id;
    }
    public java.lang.String getTitle()
    {
        return this.title;
    }
    public void setTitle(java.lang.String title)
    {
        this.title = title;
    }
    public String getStartTime()
    {
        return this.startTime;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }
    public String getEndTime()
    {
        return this.endTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    public java.lang.String getAllDay()
    {
        return this.allDay;
    }
    public void setAllDay(java.lang.String allDay)
    {
        this.allDay = allDay;
    }
    public java.lang.String getColor()
    {
        return this.color;
    }
    public void setColor(java.lang.String color)
    {
        this.color = color;
    }
    public java.lang.String getUserID()
    {
        return this.userID;
    }
    public void setUserID(java.lang.String userID)
    {
        this.userID = userID;
    }
    public String getCreateTime()
    {
        return this.createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    public java.lang.String getIsFinish()
    {
        return isFinish;
    }
    public void setIsFinish(java.lang.String isFinish)
    {
        this.isFinish = isFinish;
    }
}
