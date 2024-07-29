package com.company.lnctteachersschedule;

public class Teacher {
    private String name;
    private String teacherCode;
    private String contactNo;

    public Teacher(String name, String teacherCode, String contactNo) {
        this.name = name;
        this.teacherCode = teacherCode;
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", teacherCode='" + teacherCode + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
