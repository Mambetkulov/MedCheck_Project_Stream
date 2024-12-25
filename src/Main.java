import Models.Department;
import Models.Doctor;
import Models.Hospital;
import Models.Patient;
import dao.DepartamentDaoImp;
import dao.DoctorDaoImp;
import dao.HospitalDaoImp;
import dao.PatientDaoImp;
import database.GenerateId;
import enumgender.Gender;
import service.DepartamentServiceImp;
import service.DoctorServiceImp;
import service.HospitalServiceImp;
import service.PatientServiceImp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static Scanner scan2 = new Scanner(System.in);
    public static DepartamentDaoImp departamentDaoImp = new DepartamentDaoImp();
    public static DepartamentServiceImp department = new DepartamentServiceImp(departamentDaoImp);
    public static HospitalDaoImp hospitalDaoImp = new HospitalDaoImp();
    public static HospitalServiceImp hospitalServiceImp = new HospitalServiceImp(hospitalDaoImp);
    public static DoctorDaoImp doctorDaoImp = new DoctorDaoImp();
    public static DoctorServiceImp doctorServiceImp = new DoctorServiceImp(doctorDaoImp);
    public static PatientDaoImp patientDaoImp = new PatientDaoImp();
    public static PatientServiceImp patientServiceImp = new PatientServiceImp(patientDaoImp);
    public static void main(String[] args) {
        boolean TurnOff = true;
        while(TurnOff){
            int choice = 0;
            boolean sw = true;

            System.out.println("""
                        \n
                        1 add hospital
                        2 find hospital by id
                        3 get all hospital
                        4 get all patient from hospital
                        5 delete hospital by id
                        6 get all hospital by address
                        7 add patient
                        8 remove patient by id
                        9 update patient by id
                        10 add patients to hospital
                        11 get patient by id
                        12 get patient by age
                        13 sort patients by age
                        14 add doctor
                        15 remove doctor by id
                        16 update doctor by id
                        17 find doctor by id
                        18 assign doctor to department
                        19 get all doctors by hospital  id
                        20 get all doctors by department id
                        21 add department
                        22 remove department
                        23 update department
                        24 get all department by hospital id
                        25 find department by name
                        26 back
                        \n
                """);

            do{
                try{
                    choice = new Scanner(System.in).nextInt();
                    sw = false;
                }catch (InputMismatchException e){
                    System.out.println("Only numbers");
                }

            }while(sw);

            switch (choice){
                case 1 : {
                    System.out.println( hospitalServiceImp.addHospital(hospitalBlanke()));
                    break;
                }case 2 : {

                    System.out.println("Write hospital id");
                    try{
                        Long id = new Scanner(System.in).nextLong();
                        System.out.println(hospitalServiceImp.findHospitalById(id));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }

                    break;
                }case 3 : {
                    System.out.println(hospitalServiceImp.getAllHospital());
                    break;
                }case 4 : {
                    System.out.println("Write hospital id");
                    try{
                        Long id1 = new Scanner(System.in).nextLong();
                        System.out.println(hospitalServiceImp.getAllPatientFromHospital(id1));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 5 : {
                    System.out.println("Write hospital id");
                    try{
                        Long id2 = new Scanner(System.in).nextLong();
                        System.out.println(hospitalServiceImp.deleteHospitalById(id2));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 6 : {
                    System.out.println("Write hospital address");
                    String address = scan2.nextLine();
                    System.out.println(hospitalServiceImp.getAllHospitalByAddress(address));
                    break;
                }case 7 : {
                    System.out.println("Write hospital id");
                    try{
                        Long id3 = new Scanner(System.in).nextLong();
                        System.out.println(patientServiceImp.add(id3,patientBlanke()));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }

                    break;
                }case 8 : {
                    System.out.println("Write patient id");
                    try{
                        Long id4 = new Scanner(System.in).nextLong();
                        patientServiceImp.removeById(id4);
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }

                    break;
                }case 9 : {
                    System.out.println("Write patient id");
                    try{
                        Long id5 = new Scanner(System.in).nextLong();
                        System.out.println(patientServiceImp.updateById(id5,patientBlanke()));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }

                    break;
                }case 10 : {
                    List<Patient> patients = new ArrayList<>();
                    System.out.println("Write hospital id");
                    try{
                        Long id = new Scanner(System.in).nextLong();
                        System.out.println("Write count of patients");
                        int count = new Scanner(System.in).nextInt();
                        for (int i = 0; i < count; i++) {
                            patients.add(patientBlanke());
                            System.out.println("\nPatient number - " + i + 1);
                        }
                        System.out.println(patientServiceImp.addPatientsToHospital(id,patients));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 11 : {
                    System.out.println("Write patient id");
                    try{
                        Long id6 = new Scanner(System.in).nextLong();
                        System.out.println(patientServiceImp.getPatientById(id6));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }

                    break;
                }case 12 : {
                    System.out.println(patientServiceImp.getPatientByAge());
                    break;
                }case 13 : {
                    System.out.println("""
                            Write A to sort by A-Z
                            Write D to sort by Z-A
                            """);
                    String command = scan2.nextLine();
                    if(command.equalsIgnoreCase("A")){
                        System.out.println(patientServiceImp.sortPatientsByAge(command));
                    }else if(command.equalsIgnoreCase("D")){
                        System.out.println(patientServiceImp.sortPatientsByAge(command));
                    }else{
                        System.out.println("Invalid command");
                    }
                    break;
                }case 14 : {
                    System.out.println("Write hospital id");
                    try{
                        Long id7 = new Scanner(System.in).nextLong();
                        System.out.println(doctorServiceImp.add(id7,doctorBlanke()));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 15 : {
                    System.out.println("Write Doctor id");
                    try{
                        Long id8 = new Scanner(System.in).nextLong();
                        doctorServiceImp.removeById(id8);
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 16 : {
                    System.out.println("Write Doctor id");
                    try{
                        Long id9 = new Scanner(System.in).nextLong();
                        System.out.println(doctorServiceImp.updateById(id9,doctorBlanke()));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 17 : {
                    System.out.println("Write doctor id");
                    try{
                        Long id0 = new Scanner(System.in).nextLong();
                        System.out.println(doctorServiceImp.findDoctorById(id0));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 18 : {
                    boolean isTrue = true;
                    List<Long> doctorid = new ArrayList<>();
                    Long id = 0L;
                    do{
                        try {
                            System.out.println("Write department id");
                            id = new Scanner(System.in).nextLong();
                        }catch (InputMismatchException j){
                            System.out.println("only numbers");
                        }
                        try{

                            System.out.println("write count of doctors");
                            int count = new Scanner(System.in).nextInt();
                            System.out.println("Write doctors id");
                            for (int i = 0; i < count; i++) {
                                Long id2 = new Scanner(System.in).nextLong();
                                doctorid.add(id2);
                                System.out.println(i + 1 + ") Doctor with id : " + id2);
                            }
                            System.out.println(doctorServiceImp.assignDoctorToDepartment(id,doctorid));
                            isTrue = false;
                        }catch (InputMismatchException e){
                            System.out.println("Only numbers");
                        }

                    }while(isTrue);
                    break;
                }case 19 : {
                    System.out.println("Write hospital id");
                    try{
                        Long id = new Scanner(System.in).nextLong();
                        System.out.println(doctorServiceImp.getAllDoctorsByHospitalId(id));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 20 : {
                    System.out.println("Write department id");
                    try{
                        Long id = new Scanner(System.in).nextLong();
                        System.out.println(doctorServiceImp.getAllDoctorsByDepartmentId(id));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 21 : {
                    System.out.println("Write hospital id");
                    try{
                        Long id = new Scanner(System.in).nextLong();
                        System.out.println(department.add(id,departmentBlank()));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 22 : {
                    System.out.println("Write department id");
                    try{
                        Long id = new Scanner(System.in).nextLong();
                        department.removeById(id);
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 23 : {
                    System.out.println("Write department id");
                    try{
                        Long id5 = new Scanner(System.in).nextLong();
                        System.out.println(department.updateById(id5,departmentBlank()));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                } case 24 : {
                    System.out.println("Write hospital id");
                    try{
                        Long id = new Scanner(System.in).nextLong();
                        System.out.println(department.getAllDepartmentByHospital(id));
                    }catch (InputMismatchException e){
                        System.out.println("Only numbers");
                    }
                    break;
                }case 25 : {
                    System.out.println("Write department name");
                    String name = scan2.nextLine();
                    System.out.println(department.findDepartmentByName(name));
                    break;
                }case 26 : {
                    TurnOff = false;
                    break;
                }
                default:{
                    System.out.println("Invalid command");
                }

            }
        }



    }
    public static Doctor doctorBlanke(){

        System.out.println("Write  first name ");
        String name = scan.nextLine();
        System.out.println("Write last name ");
        String surname = scan.nextLine();
        Gender gen = null ;
        int workE = 0;
        boolean  isTrue = true;
        do{
            System.out.println("""
                Gender
                1 > Male
                2 > Female
                """);
            try {
                int gender = new Scanner(System.in).nextInt();
                System.out.println("write work experience");
                workE = new Scanner(System.in).nextInt();
                if(gender == 1){
                    gen = Gender.MALE;
                    isTrue = false;
                }else if(gender == 2){
                    isTrue = false;
                    gen = Gender.FEMALE;
                }else {
                    System.out.println("Invalid command");
                }

            }catch (InputMismatchException e){
                System.out.println("Only numbers");
            }
        }while (isTrue);
        return new Doctor(GenerateId.docId(),name,surname,gen,workE);

    }

    public static Hospital hospitalBlanke(){
        List<Doctor> doctorList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();

        System.out.println("Write hospital name ");
        String name = scan.nextLine();
        System.out.println("Write address");
        String address = scan.nextLine();

        return new Hospital(GenerateId.hosId(),name,address,departmentList,doctorList,patients);
    }

    public static Patient patientBlanke (){

        boolean isTrue = true;
        System.out.println("write patient first name");
        String name = scan.nextLine();
        System.out.println("write patient last name");
        String lastName = scan.nextLine();
        int age = 0;
        do {
            try {
                System.out.println("write patient age");
                age = new Scanner(System.in).nextInt();
                isTrue = false;
            } catch (InputMismatchException e) {
                System.out.println("only numbers");
            }
        }while(isTrue);
        isTrue = true;
        Gender gen = null ;
        do{
            System.out.println("""
                Gender
                1 > Male
                2 > Female
                """);
            try {
                int gender = new Scanner(System.in).nextInt();
                if(gender == 1){
                    gen = Gender.MALE;
                    isTrue = false;
                }else if(gender == 2){
                    isTrue = false;
                    gen = Gender.FEMALE;
                }else {
                    System.out.println("Invalid command");
                }

            }catch (InputMismatchException e){
                System.out.println("Only numbers");
            }
        }while (isTrue);

        return new Patient(GenerateId.patId(),name,lastName,age,gen);

    }

    public static Department departmentBlank(){
        List<Doctor> doctors = new ArrayList<>();
        System.out.println("write department name");
        String name = scan.nextLine();

        return new Department(GenerateId.depId(),name,doctors);

    }




}