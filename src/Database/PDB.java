package Database;

import java.sql.*;
import java.util.ArrayList;

public class PDB extends DB{

    public static int add(Product product) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO products VALUES("
                + "'" + product.name + "', "
                + product.price + ", "
                + product.offerPrice + ", "
                + "'" + product.expireDate + "', "
                + "'" + product.type + "', "
                + "'" + product.addedAt + "', "
                + ")";

        DMLQuery(query);

        return getLastRecordIdAdded("products");
    }

    public static void delete(int productId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM products WHERE id = " + productId;

        DMLQuery(query);
    }

    public static void update(Product product) throws SQLException, ClassNotFoundException {
        String query = "UPDATE products SET "
                + " name = '" + product.name + "', "
                + " price = " + product.price + ", "
                + " offerPrice = " + product.offerPrice + ", "
                + " expireDate = '" + product.expireDate + "', "
                + " type = '" + product.type + "', "
                + " addedAt = '" + product.addedAt + "', "  
                + " WHERE id = " + product.id;

        DMLQuery(query);
    }

    public static ArrayList<Product> search(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE " + formatCondition(condition);

        ResultSet res = DQLQuery(query);
        
        ArrayList<Product> usersRes = new ArrayList<>();
        while (res.next()) {
            usersRes.add(new Product(
                    res.getInt("id"),
                    res.getNString("name"),
                    res.getFloat("price"),
                    res.getFloat("offerPrice"),
                    res.getDate("expireDate"),
                    res.getDate("addedAt"),
                    res.getNString("type")
            ));
        }
        
        return usersRes;
    }
}