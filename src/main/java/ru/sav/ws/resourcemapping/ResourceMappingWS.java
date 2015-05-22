package ru.sav.ws.resourcemapping;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebService()
public class ResourceMappingWS {

    @WebMethod(action = "getMappings", operationName = "getMappings")
    public List<Mapping> getMappings() {
        List<Mapping> mappings = new ArrayList<Mapping>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://doc.core:5432/olat_dev", "olat", "olat");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select\n" +
                    "        r.resid,\n" +
                    "        md5('IFrameDisplayController:/opt/openolat/olatdata_dev/bcroot/course/' || r.resid || '/coursefolder') as hash\n" +
                    "    from o_olatresource r\n" +
                    "        inner join o_repositoryentry re on re.fk_olatresource = r.resource_id\n" +
                    "    where r.resname = 'CourseModule'\n");

            while (resultSet.next()) {
                mappings.add(new Mapping(resultSet.getString("resid"), resultSet.getString("hash")));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mappings;
    }

    @WebMethod(action = "ping", operationName = "ping")
    public String ping(String from) {
        return "fuck you, " + from;
    }

}

