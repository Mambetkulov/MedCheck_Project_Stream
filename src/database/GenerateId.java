package database;

public class GenerateId {
    private static Long hospitalId = 1L;
    private static Long departamentId = 1L;
    private static Long doctorId = 1L;
    private static Long patientId = 1L;
    private static int generatKey = 1;

    public static Long hosId (){
        return hospitalId ++;
    }

    public static Long depId (){
        return departamentId ++;
    }

    public static Long docId (){
        return doctorId ++;
    }

    public static Long patId (){
        return patientId ++;
    }

    public static int generateKey(){return generatKey ++; }
}
