package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Product;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IGenericService<Product,Integer> {

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt =conn.prepareCall("{call getAllProducts}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        Product product = null;
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call findProductById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return product;
    }

    @Override
    public void save(Product product) {
        Connection conn = null;
        try {
            if(product.getId()==0){
                conn = ConnectDB.getConnection();
                CallableStatement callSt = conn.prepareCall("{call insertProduct(?,?,?,?,?,?,?)}");
                callSt.setString(1,product.getProduct_name());
                callSt.setString(2,product.getImage_url());
                callSt.setString(3,product.getDescription());
                callSt.setInt(4, product.getStock());
                callSt.setInt(5,product.getCatalog());
                callSt.setDouble(6,product.getImport_price());
                callSt.setDouble(7,product.getExport_price());
                callSt.executeUpdate();

            }else {
                conn = ConnectDB.getConnection();
                CallableStatement callSt = conn.prepareCall("{call updateProduct(?,?,?,?,?,?,?,?)}");
                callSt.setInt(1,product.getId());
                callSt.setString(2,product.getProduct_name());
                callSt.setString(3,product.getImage_url());
                callSt.setString(4,product.getDescription());
                callSt.setInt(5, product.getStock());
                callSt.setInt(6,product.getCatalog());
                callSt.setDouble(7,product.getImport_price());
                callSt.setDouble(8,product.getExport_price());
                callSt.executeUpdate();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call deleteProduct(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
