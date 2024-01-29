package sk.kasv.huzvare;


import java.sql.*;
import java.util.ArrayList;

public class DataControl {
    private final String URL="jdbc:mysql://localhost:3306/world_x";
    private final String USERNAME ="root";
    private final String PASSWD ="Marek2023!";
    private final String SELECT_ALL_COUNTRIES="select name from country";

    private ArrayList<String> Countries = new ArrayList<>();
    private ArrayList<String> Cities = new ArrayList<>();

    public Connection connectToDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWD);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getCity(String country){
        Cities.clear();
        Connection con = connectToDB();

        if(con==null){
            System.out.println("ERROR connection to DB");
            return null;
        }

        try {
            PreparedStatement ps = con.prepareStatement("SELECT City.Name FROM `city` left join country on CountryCode = CODE where country.name = ? ;");
            ps.setString(1,country);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Cities.add(rs.getString("Name"));
            }

            con.close();
            return Cities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getAllCountries(){
        Connection con = connectToDB();
        if(con==null){
            System.out.println("ERROR connection to DB");
            return null;
        }

        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_COUNTRIES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Countries.add(rs.getString("Name"));
            }

            con.close();
            return Countries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPopulation(String cityName){
        int Population = 0;
        Connection con = connectToDB();

        if(con==null){
            System.out.println("ERROR connection to DB");
            return 0;
        }

        try {
            PreparedStatement ps = con.prepareStatement("SELECT JSON_EXTRACT(Info,'$.Population') as Population FROM `city` where name = ? ;");
            ps.setString(1,cityName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Population = rs.getInt("Population");
            }

            con.close();
            return Population;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
