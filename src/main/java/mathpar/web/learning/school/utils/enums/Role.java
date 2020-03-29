package mathpar.web.learning.school.utils.enums;


import mathpar.web.learning.school.utils.exceptions.MalformedDataException;

public enum Role {
    Director, HeadTeacher, Teacher, Student;
    public boolean hasTeacherRole(){
        return this.equals(Director) || this.equals(HeadTeacher) || this.equals(Teacher);
    }

    public boolean canAdministrate(){
        return this.equals(Director) || this.equals(HeadTeacher);
    }

    public static Role of(String userType){
        for(Role type: Role.values()){
            if(type.name().equals(userType)) return type;
        }
        throw new MalformedDataException(String.format("Invalid string  %s, can't find appropriate school user type", userType));
    }
}
