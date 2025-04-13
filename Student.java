import java.sql.*;
import java.util.Scanner;

class Student{
    final static String url="jdbc:mysql://localhost:3306/Register";
    final static String user="root";
    final static String pass="";

    public static void main(String[] args) {
        Connection conn;
        Scanner s=new Scanner(System.in);
        try {
            //Make a Connection
            //Load a Register or Driver Class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //make a connection
            conn=DriverManager.getConnection(url,user,pass);

            System.out.println("1 New Student\n2 Read Data\n3 Read Data by ID\n4 Update the Student Data\n5 Remove the student\n6 Exit\n");

            while (true){
                System.out.print("Enter a No:");
                int n=s.nextInt();
                if(n==1){
                    //New Student
                    System.out.println("\tEnter a Student Data");

                    System.out.print("\tEnter a Name: ");
                    s.nextLine();
                    String name = s.nextLine();

                    System.out.print("\tEnter a Age: ");
                    int age = s.nextInt();

                    System.out.print("\tEnter a Gender(Male/Female): ");
                    s.nextLine();
                    String gen = s.nextLine();

                    System.out.print("\tEnter a Email: ");
                    String email = s.nextLine();

                    System.out.print("\tEnter a Phone No: ");
                    String phone = s.nextLine();

                    System.out.print("\tEnter a Course Name: ");
                    String course = s.nextLine();

                    System.out.print("\tEnter a Address: ");
                    String Add = s.nextLine();
                    try {
                        String sql1 = "INSERT INTO student (name, age, gender, email, phone, course, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement smt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

                        smt.setString(1, name);
                        smt.setInt(2, age);
                        smt.setString(3, gen);
                        smt.setString(4, email);
                        smt.setString(5, phone);
                        smt.setString(6, course);
                        smt.setString(7, Add);

                        int i = smt.executeUpdate();

                        if (i > 0) {
                            ResultSet rs = smt.getGeneratedKeys();
                            if (rs.next()) {
                                System.out.println("\t\tStudent Registered Successfully with ID: " + rs.getInt(1));
                            }
                            rs.close();
                        }
                        smt.close();
                    }
                    catch (Exception e){
                        System.out.println("Error : "+e.getMessage());
                    }
                    }
                else if(n==2){
                    System.out.println("\tAll Student Data");
                    String sql="SELECT * FROM student";
                    PreparedStatement smt=conn.prepareStatement(sql);
                    ResultSet rs=smt.executeQuery();
                    while(rs.next()){
                        System.out.println("\tID : "+rs.getInt("id")+"\n\tName : "+rs.getString("name")+"\n\tAge : "+rs.getInt("age")+"\n\tGender : "+rs.getString("gender")+"\n\tEmail : "+rs.getString("email")+"\n\tPhone No : "+rs.getString("phone")+"\n\tCourse : "+rs.getString("course")+"\n\tAddress : "+rs.getString("address")+"\n");
                    }
                    smt.close();
                }
                else if(n==3){
                    System.out.println("\tStudent Data By ID");
                    System.out.print("\tEnter a ID");
                    int id=s.nextInt();
                    String sql="SELECT * FROM student WHERE id=?";
                    PreparedStatement smt=conn.prepareStatement(sql);
                    smt.setInt(1,id);
                    ResultSet rs=smt.executeQuery();
                    boolean flag=false;
                    while(rs.next()){
                        flag=true;
                        System.out.println("\t\tID : "+rs.getInt("id")+"\n\t\tName : "+rs.getString("name")+"\n\t\tAge : "+rs.getInt("age")+"\n\t\tGender : "+rs.getString("gender")+"\n\t\tEmail : "+rs.getString("email")+"\n\t\tPhone No : "+rs.getString("phone")+"\n\t\tCourse : "+rs.getString("course")+"\n\t\tAddress : "+rs.getString("address")+"\n");
                    }
                    if (!flag) {
                        System.out.println("\t\t❌ No student found with ID: " + id);
                    }

                    rs.close();
                    smt.close();
                }
                else if(n==4){
                    System.out.println("\tUpdate Data By ID");
                    System.out.print("\tEnter a ID");
                    int id=s.nextInt();
                    String sql="SELECT * FROM student WHERE id=?";
                    PreparedStatement smt=conn.prepareStatement(sql);
                    smt.setInt(1,id);
                    ResultSet rs=smt.executeQuery();
                    String name="";
                    int age=0;
                    String gender="";
                    String email="";
                    String phone="";
                    String course="";
                    String address = "";
                    boolean flag=false;
                    while(rs.next()){
                        flag=true;
                        name=rs.getString("name");
                        age=rs.getInt("age");
                        gender=rs.getString("gender");
                        email=rs.getString("email");
                        phone=rs.getString("phone");
                        course=rs.getString("course");
                        address=rs.getString("address");
                        System.out.println("\t\t1 Name : "+name+"\n\t\t2 Age : "+age+"\n\t\t3 Gender : "+gender+"\n\t\t4 Email : "+email+"\n\t\t5 Phone No : "+phone+"\n\t\t6 Course : "+course+"\n\t\t7 Address : "+address+"\n");
                    }
                    if (!flag) {
                        System.out.println("\t\t❌ No student found with ID: " + id);
                    }
                    else {
                        while (true) {
                            System.out.print("\t\tSelect The Column No(8 for Update): ");
                            int cn=s.nextInt();
                            if(cn==1){
                                System.out.print("\t\t\tEnter a New Name: ");
                                s.nextLine();
                                name=s.nextLine();
                            }
                            else if(cn==2){
                                System.out.print("\t\t\tEnter a New Age: ");
                                age=s.nextInt();
                            }
                            else if(cn==3){
                                System.out.print("\t\t\tEnter a New Gender: ");
                                s.nextLine();
                                gender=s.nextLine();
                            }
                            else if(cn==4){
                                System.out.print("\t\t\tEnter a New Email: ");
                                s.nextLine();
                                email=s.nextLine();
                            }
                            else if(cn==5){
                                System.out.print("\t\t\tEnter a New Phone No: ");
                                s.nextLine();
                                phone=s.nextLine();
                            }
                            else if(cn==6){
                                System.out.print("\t\t\tEnter a New Course: ");
                                s.nextLine();
                                course=s.nextLine();
                            } else if (cn==7) {
                                System.out.print("\t\t\tEnter a New Address: ");
                                s.nextLine();
                                address=s.nextLine();
                            } else if (cn==8) {
                                break;
                            }
                            else {
                                System.out.println("\t\t\tRe Enter a Chooses");
                            }
                        }
                        try {
                            String upsql = "UPDATE student SET name = ?, age = ?,gender=?, email = ? , phone=?, course=?, address=? WHERE id = ?";
                            PreparedStatement upsmt = conn.prepareStatement(upsql);
                            upsmt.setString(1, name);
                            upsmt.setInt(2, age);
                            upsmt.setString(3, gender);
                            upsmt.setString(4, email);
                            upsmt.setString(5, phone);
                            upsmt.setString(6, course);
                            upsmt.setString(7, address);
                            upsmt.setInt(8, id);
                            int res = upsmt.executeUpdate();
                            if (res > 0) {
                                System.out.println("\t\t\t\tUpdate Successfully");
                            } else {
                                System.out.println("\t\t\t\t❌Not Update");
                            }
                        }
                        catch (Exception e){
                            System.out.println("Error :"+e.getMessage());
                        }
                    }
                }
                else if(n==5){
                    System.out.println("\tRemove the Student");
                    System.out.print("\tEnter a ID");
                    int id=s.nextInt();
                    String sql="DELETE FROM student where id=?";
                    PreparedStatement smt=conn.prepareStatement(sql);
                    smt.setInt(1,id);
                    int x=smt.executeUpdate();
                    if (x>0) {
                        System.out.println("\t\tStudent Successful deleted : " + id);
                    }
                    else {
                        System.out.println("\t\t❌ No student found with ID: " + id);
                    }
                    smt.close();
                }
                else if(n==6){
                    break;
                }
                else {
                    System.out.println("Re-Enter a Correct No");
                }
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println("Database Connection Error : "+e);
        }
    }
}