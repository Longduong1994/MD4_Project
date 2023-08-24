package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.Order;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService implements IGenericService<Order,Integer> {
    @Autowired
    BillingService billingService;
    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getAllOrders}");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUser_id(resultSet.getInt("user_id"));
                order.setCartId(resultSet.getInt("order_detail_id"));
                order.setExport_Date(resultSet.getDate("export_date"));
                order.setStatus(resultSet.getBoolean("status"));
                orders.add(order);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        Connection connection = null;
        Order order = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{findOrderById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            while(resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setUser_id(resultSet.getInt("user_id"));
                order.setCartId(resultSet.getInt("order_detail_id"));
                order.setExport_Date(resultSet.getDate("export_date"));
                order.setStatus(resultSet.getBoolean("status"));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
         ConnectDB.closeConnection(connection);
        }
        return order;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call deletelOrder(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
